package com.jisun.outstargram.Service;


import com.jisun.outstargram.constant.Role;
import com.jisun.outstargram.dto.JoinDto;
import com.jisun.outstargram.dto.MemberProfileDto;
import com.jisun.outstargram.dto.UpdateMemberDto;
import com.jisun.outstargram.entity.Member;
import com.jisun.outstargram.repository.MemberRepository;
import com.jisun.outstargram.repository.SubscribeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member 회원가입_서비스(JoinDto joinDto){
        Member dbJoinMember = Member.builder()  //dto가 아닌 엔티티를 던져야함!!! : 빌더를 통해 dto를 entity로 변경~
                                .userId(joinDto.getUserId())
                                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                                .role(Role.ROLE_USER)
                                .email(joinDto.getEmail())
                                .name(joinDto.getName())
                                .build();

        return memberRepository.save(dbJoinMember);
    }

    @Transactional //더티체킹
    public void  회원정보수정_서비스(int id ,UpdateMemberDto updateMemberDto){
        Optional<Member> findMember =  memberRepository.findById(id); //아이디로 넘기기
        if(findMember.isPresent()){
            Member member = findMember.get(); //엔티티!!!
                    member.setMbti(updateMemberDto.getMbti());
                    member.setPhone(updateMemberDto.getPhone());
                    member.setEmail(updateMemberDto.getEmail());
                    member.setDescription(updateMemberDto.getDescription());
                    member.setName(updateMemberDto.getName());
        } else {
            throw new UsernameNotFoundException("회원정보수정에 실패하였습니다."); //else 안으로 들어와야함~~
        }
    }


    @Value("${file.path}") //어노테이션 not 롬목
    private String uploadFolder;

    @Transactional //더티체킹
    public Member 프로필이미지업데이트_서비스(int id, MultipartFile profileImageUrl) {

        //이미지생성
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + profileImageUrl.getOriginalFilename();
        String thumbnailFileName = "thumb_" + imageFileName;

        Path imageFilePath = Paths.get(uploadFolder + imageFileName); //yml에서 미리 경로를 지정해둠~~!!
        File originalFile = new File(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath, profileImageUrl.getBytes()); //getBytes는 항상 트라이-캐치를 동반한다
            //프로필 사진 작게 보기~~~
            Thumbnailator.createThumbnail(originalFile,
                                          new File(uploadFolder + thumbnailFileName),150,150);
            originalFile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Optional<Member> optionalMember = memberRepository.findById(id); //셀렉트 -> 멤버
        if(optionalMember.isPresent()) {
            optionalMember.get().setProfileImageUrl(thumbnailFileName);
            return optionalMember.get(); // -> 멤버로 리턴함
        } else {
            throw new UsernameNotFoundException("프로필이미지업로드에 실패하셨습니다.");
        }
    }


    public MemberProfileDto getProfile(int id, int customerDetailsId) {
        MemberProfileDto memberProfileDto = new MemberProfileDto();
        Member  memberInfo =
                memberRepository.findById(id).orElseThrow(
                        ()-> new UsernameNotFoundException("없는 사용자 입니다.")
                );
        int subscribeCount = subscribeRepository.구독자수_레포지토리(id);
        int subscribeState = subscribeRepository.구독확인_레포지토리(customerDetailsId, id); //구독안했으면==0 / 구독했으면==1


        memberProfileDto.setPageOwner(id==customerDetailsId);
        memberProfileDto.setMember(memberInfo);
        memberProfileDto.setSubscribeCount(subscribeCount);
        memberProfileDto.setSubscribeState(subscribeState >= 1);
        return memberProfileDto;
    }



}
