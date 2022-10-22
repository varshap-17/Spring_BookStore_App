package com.example.bookstore.service;

import com.example.bookstore.exception.BookstoreException;
import com.example.bookstore.model.Email;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements IEmailService{
    @Override
    public String sendEmail(Email email)throws MessagingException{
        final String fromEmail="varshap630@gmail.com";
        final String fromPassword="wibhnedyqsiqqwor";
        Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Authenticator authenticator=new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail,fromPassword);
            }
        };
        Session session=Session.getInstance(properties,authenticator);
        MimeMessage mail=new MimeMessage(session);
        try {
            mail.addHeader("Content-type","text/HTML;charset=UTF-8");
            mail.addHeader("format","flowed");
            mail.addHeader("Content-Transfer-Encoding","8bit");
            mail.setFrom(new InternetAddress(fromEmail));
            mail.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getTo()));
            mail.setText(email.getBody(),"UTF-8");
            mail.setSubject(email.getSubject(),"UTF-8");
            Transport.send(mail);
            return "Mail sent!";
        }catch (MessagingException e){
            e.printStackTrace();
        }
        throw new BookstoreException("ERROR:Couldn't send mail!");
    }
    @Override
    public String getLink(String token){
        return "http://localhost:8086/user/verify/"+token;
    }
}
