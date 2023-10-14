package com.example.smtpserver.demo;

import com.example.smtpserver.demo.Entity.EmailDetails;
import com.example.smtpserver.demo.Service.EmailService;
import com.example.smtpserver.demo.Service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private EmailServiceImpl emailService;

    @Test
    void emailSenderTest() {

        String messageBody = """
                You requested for a password change. If you don't recognize this activity report here. For changing
                your password click on the link below:https://test.com
                """;
        String attachment = """
                Here is your attachment
                """;
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setAttachment(attachment);
        emailDetails.setMsgBody(messageBody);
        emailDetails.setRecipient("nirajanach3@gmail.com");
        emailDetails.setSubject("Reset password here");

        emailService.sendSimpleMail(emailDetails);
    }

}
