package com.tttn.flowershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class MailService {

  @Autowired
  private JavaMailSender javaMailSender;

  public MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendEmail(String from, String to, String subject, String body) {

    try {
      MimeMessage mail = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");

      helper.setFrom(from, from);
      helper.setTo(to);
      helper.setReplyTo(from, from);
      helper.setSubject(subject);
      helper.setText(body, true);

      javaMailSender.send(mail);
    } catch (Exception e) {
      // TODO: handle exception
      throw new RuntimeException(e);
    }
  }

  void sendEmailWithAttachment() throws MessagingException, IOException {

    MimeMessage msg = javaMailSender.createMimeMessage();

    // true = multipart message
    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    helper.setTo("1@gmail.com");

    helper.setSubject("Testing from Spring Boot");

    // default = text/plain
    //helper.setText("Check attachment for image!");

    // true = text/html
    helper.setText("<h1>Check attachment for image!</h1>", true);

    helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

    javaMailSender.send(msg);

  }
}
