package com.example.smtpserver.demo.Service;

import com.example.smtpserver.demo.Entity.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String sender;

    @Override
    public String sendSimpleMail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMsgBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            return "Mail sent Successfully........";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setText(emailDetails.getMsgBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            FileSystemResource fileSystemResource = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource.getFile());

            javaMailSender.send(mimeMessage);
            return "Email sent successfully....";

        } catch (MessagingException e) {
            return "Error while sending mail!!!";
        }
    }
}
