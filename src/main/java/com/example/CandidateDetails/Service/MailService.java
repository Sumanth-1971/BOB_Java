package com.example.CandidateDetails.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    public String sendSimpleEmail(String toEmail, String subject, String template, Map<String, Object> variables) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(template, context);

//            helper.setFrom("spring.learn6@gmail.com");
            helper.setFrom("spring.learn6@gmail.com", "Do Not Reply");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            javaMailSender.send(message);
            return "Mail Sent!";
        } catch (Exception e) {
            return "Error while sending email: " + e.getMessage();
        }
    }


   public String sendEmailWithAttachment(String toEmail, String subject,String attachment,Map<String,Object> variables,String template) throws MessagingException, UnsupportedEncodingException {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            Context context = new Context();
            context.setVariables(variables);

            String htmlContent = templateEngine.process(template, context);
            helper.setFrom("spring.learn6@gmail.com", "Do Not Reply");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));

            helper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            javaMailSender.send(message);

            return "Mail Sent with attachment!";
        }
        catch (Exception e){
            return "Error sending email due to"+e.getMessage();
        }
    }
}
