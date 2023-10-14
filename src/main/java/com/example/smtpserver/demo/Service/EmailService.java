package com.example.smtpserver.demo.Service;

import com.example.smtpserver.demo.Entity.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails emailDetails);

    String sendMailWithAttachment(EmailDetails emailDetails);
}
