package ru.sbercourse.cinema.ticketoffice.utils;

import org.springframework.mail.SimpleMailMessage;

public class MailUtils {

  public static SimpleMailMessage createEmailMessage(
      String emailFrom,
      String emailTo,
      String subject,
      String text
  ) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailFrom);
    message.setTo(emailTo);
    message.setSubject(subject);
    message.setText(text);
    return message;
  }
}
