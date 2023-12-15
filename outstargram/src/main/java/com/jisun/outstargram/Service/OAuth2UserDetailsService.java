package com.jisun.outstargram.Service;

import com.jisun.outstargram.constant.Role;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Member;
import com.jisun.outstargram.repository.MemberRepository;
import com.jisun.outstargram.social.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.jisun.outstargram.entity.Member.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //log.info("구글 로그인 하면 여기로 들어온다. 여기서 필요한 작업 하면 된다.");
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User.getAttributes()==={}",oAuth2User.getAttributes());
        log.info("userRequest======{}",userRequest.getClientRegistration().getRegistrationId());
        Map<String,Object> oAuth2UserInfo = (Map)oAuth2User.getAttributes();

        SocialUserInfo socialUserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();
//        String email = null;
//        String nickName=  null;
//        String userId = null;
        if(provider.equals("google")) {
            socialUserInfo = new GoogleUserInfo(oAuth2UserInfo);
            //email = (String)oAuth2UserInfo.get("email");
            //nickName =  (String)oAuth2UserInfo.get("name");
            //userId = provider+"_"+(String)oAuth2UserInfo.get("sub");
        } else  if(provider.equals("naver")) {
            socialUserInfo = new NaverUserInfo((Map)oAuth2UserInfo.get("response"));
//            Map<String, Object> naverResponse = (Map)oAuth2UserInfo.get("response");
//            email = (String)naverResponse.get("email");
//            nickName =  (String)naverResponse.get("nickname");
//            userId = provider+"_"+(String)naverResponse.get("id");
        } else if(provider.equals("kakao")) {
            socialUserInfo = new KakaoUserInfo(oAuth2UserInfo);
//            Map<String, Object> kakaoResponse = (Map)oAuth2UserInfo.get("properties");
//            email = (String)kakaoResponse.get("email");
//            nickName =  (String)kakaoResponse.get("nickname");
//            userId = provider+"_"+oAuth2UserInfo.get("id");
        }
        else if(provider.equals("github")) {
            socialUserInfo = new GithubUserInfo(oAuth2UserInfo);
//            Map<String, Object> kakaoResponse = (Map)oAuth2UserInfo.get("properties");
//            email = (String)kakaoResponse.get("email");
//            nickName =  (String)kakaoResponse.get("nickname");
//            userId = provider+"_"+oAuth2UserInfo.get("id");
        }
        String email = socialUserInfo.getEmail();
        String nickName = socialUserInfo.getName();
        String userId = socialUserInfo.getProviderId();
        String role = "ROLE_USER";
        String password = bCryptPasswordEncoder.encode(UUID.randomUUID().toString());
        Member returnMember = null;
        Optional<Member> foundMember =  memberRepository.findByUserId(userId);
        if(foundMember.isPresent()) {
            log.info("있는 멤버");
            returnMember = foundMember.get();
        } else {
            log.info("없는 멤버");
            returnMember = builder()
                    .userId(userId)
                    .password(password)
                    .role(Role.valueOf(role))
                    .name(nickName)
                    .email(email)
                    .build();
            memberRepository.save(returnMember);
        }
        return new CustomUserDetails(returnMember,oAuth2User.getAttributes());
    }



}
