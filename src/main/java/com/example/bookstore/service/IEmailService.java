package com.example.bookstore.service;

import com.example.bookstore.model.Email;

import javax.mail.MessagingException;

public interface IEmailService {
    public String sendEmail(Email email)throws MessagingException;
    public String getLink(String token);
}
