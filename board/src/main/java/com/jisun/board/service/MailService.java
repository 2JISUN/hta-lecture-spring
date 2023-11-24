package com.jisun.board.service;

import com.jisun.board.dao.MemberDao;
import com.jisun.board.dto.MailDto;
import com.jisun.board.dto.UpdateDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@RequiredArgsConstructor //final
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;





    /*메일 전송*/
    public void sendMail(MailDto mailDto){
        MimeMessage message = javaMailSender.createMimeMessage(); //html 태그 인식 가능

        try {
            message.setFrom("snm03097@naver.com"); //보내는사람
            message.setRecipients(MimeMessage.RecipientType.TO, mailDto.getReceiver()); //받는사람
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getContent(),"UTF-8","html");
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


/*      SimpleMailMessage simpleMailMessage = new SimpleMailMessage(); //html 태그 인식 못함

        simpleMailMessage.setTo(mailDto.getReceiver());
        simpleMailMessage.setFrom("snm03097@naver.com");
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getContent());
        javaMailSender.send(simpleMailMessage);*/
    }









    /*랜덤번호 난수생성*/
    private String randomNumber;
    public  void createRandomNumber(){
        randomNumber = "" + ((int)(Math.random()*90000)+10000);
    }


    /*회원가입 시 메일 보내기*/
    public MimeMessage createMail(String mail){
        createRandomNumber(); //랜덤번호 생성
        MimeMessage message = javaMailSender.createMimeMessage();


        try {
            message.setFrom("snm03097@naver.com"); //보내는사람
            message.setRecipients(MimeMessage.RecipientType.TO, mail); //받는사람
            message.setSubject("이메일 검증");
            String content = "<h2>요청하신 인증번호입니다</h2>";
            content += "<h1>" + randomNumber + "</h1>";
            //content += "<h2>"+randomNumber+"</h2>";
            message.setText(content,"UTF-8","html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }


    /*회원가입 시 메일 인증*/
    public String sendAuthEmail(String mail){
        MimeMessage message = createMail(mail);
        //db비밀번호 업데이트
        javaMailSender.send(message);
        return randomNumber;
    }


    /*비밀번호 재발급*/
    @Transactional
    public void sendMailAndChangePassword(UpdateDto updateDto) {
        String randomNum = sendAuthEmail(updateDto.getEmail());  // 비밀번호 보내기....
        UpdateDto dbUpdateDto = UpdateDto.builder()
                .email(updateDto.getEmail())
                .password(bCryptPasswordEncoder.encode(randomNum))
                .build();
        memberDao.updatePassword(dbUpdateDto);
    }

}
