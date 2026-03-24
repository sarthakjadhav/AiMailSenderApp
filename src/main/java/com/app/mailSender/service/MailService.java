package com.app.mailSender.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AIService aiService;

    public void sendJobApplicationMail(String to, String company, String position) {

        try {

            String subject = "Application for " + position + " at " + company;

            System.out.println("👉 Calling AI Service...");
            String body = aiService.generateEmail(to,company, position);
            System.out.println("👉 FINAL EMAIL BODY:\n" + body);
            String contact = "\nMobile: +91 9421947067\nEmail: ssatishjadhav1@gmail.com\nCurrent salary: 5.5 LPA + Allowances\nExpected Salary: 12 LPA\n Notice period: 60 days (Negotiable and buy option available)\n";

            body += "\n"+contact;
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("ssatishjadhav1@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            // 📎 Attach resume (make sure file exists in resources)
            ClassPathResource file = new ClassPathResource("SarthakResumeSE.pdf");
            helper.addAttachment("SarthakResumeSE.pdf", file);

            mailSender.send(message);

            System.out.println("✅ Mail sent with AI + attachment");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Mail failed");
        }

    }
}