package com.BrainWorks.CO_API.utitlity;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailUtility {

    private JavaMailSender javaMailSender;

    public boolean sendEmail(String to, String body, String subject, File file) throws MessagingException {
        boolean isMailSend=false;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body,true);
        helper.addAttachment("HIS-Elgi-Notice",file);
        javaMailSender.send(mimeMessage);
        isMailSend=true;

        return  isMailSend;



    }
}
