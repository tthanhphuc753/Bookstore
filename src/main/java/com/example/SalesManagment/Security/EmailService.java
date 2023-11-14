package com.example.SalesManagment.Security;


import com.example.SalesManagment.Model.User.User;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger((EmailService.class));

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String url, User theUser) throws MessagingException, UnsupportedEncodingException {
        String subect = "Email Verification";
        String senderName = "Sales Managment Registration Portal Service";
        String content = "<p> Hi, " + theUser.getName() + ", </p> " +
                "<p> Thanks you for Registering with us, " +
                "Please, follow the link belo to complete your registration.</p>" +
                "<a href=\"" + url + "\">Verify your email to active yoour account</a>" +
                "</p> Thanks you <br> User Sales Managment Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("thenghia25022003@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subect);
        messageHelper.setText(content, true);
        mailSender.send(message);
    }
}
