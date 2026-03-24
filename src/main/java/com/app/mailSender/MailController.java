package com.app.mailSender;

import com.app.mailSender.dto.MailRequest;
import com.app.mailSender.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody MailRequest request) {

        mailService.sendJobApplicationMail(
                request.getTo(),
                request.getCompany(),
                request.getPosition()
        );

        return ResponseEntity.ok("Mail sent");
    }
}