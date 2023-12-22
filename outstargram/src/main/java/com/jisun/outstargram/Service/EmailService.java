package com.jisun.outstargram.Service;

import com.jisun.outstargram.dto.EmailDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    private String randomNumber;
    
    public void 랜덤번호생성() {
        randomNumber = ""+((int)(Math.random()*90000)+10000);
        log.info("randomNumber==={}",randomNumber);
    }

    public void 이메일보내기_서비스(EmailDto emailDto){
        랜덤번호생성();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("snm03097@naver.com");
        message.setTo(emailDto.getReceiverAddress());
        message.setSubject(emailDto.getTitle());
        message.setText(emailDto.getContent() + "/n 랜덤번호 출력" + "<h1 style='font-size:100px; color:#f00;'>"+randomNumber+"</h1>");
        javaMailSender.send(message);

    }




/*    public MimeMessage 이메일받기_서비스(EmailDto mailDto) {
        //렌덤 숫자 생성
        랜덤번호생성();
        MimeMessage message =  javaMailSender.createMimeMessage();
        try {
            message.setFrom("snm03097@naver.com");  // 보내는 사람
            message.setRecipients(MimeMessage.RecipientType.TO, receiver);  // 받는 사람
            message.setSubject("행운의 편지🍀");
            String content = "<h2>이 편지는 영국에서부터 시작한..</h2>";
            content+="<h1 style='font-size:100px; color:#f00;'>"+randomNumber+"</h1>";
            message.setText(content,"UTF-8","html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }*/


}
