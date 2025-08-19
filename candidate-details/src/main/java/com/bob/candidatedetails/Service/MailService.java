package com.bob.candidatedetails.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public String sendSimpleEmail(String toEmail, String subject, String template, Map<String, Object> variables) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(template, context);

            helper.setFrom("spring.learn6@gmail.com", "Do Not Reply");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
            return "Mail Sent!";
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }


    public String sendEmailWithAttachment(String toEmail, String subject, String path, Map<String, Object> variables, String template) throws MessagingException, UnsupportedEncodingException {
        try {

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(template, context);
            helper.setFrom("spring.learn6@gmail.com", "Do Not Reply");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);


            UrlResource pdfResource = new UrlResource(path);
            System.out.println(path);
            helper.addAttachment("OfferLetter.pdf", pdfResource);
            mailSender.send(message);

            return "Mail Sent with attachment!";
        } catch (Exception e) {
            return "Error sending email due to" + e.getMessage();
        }
    }


}
