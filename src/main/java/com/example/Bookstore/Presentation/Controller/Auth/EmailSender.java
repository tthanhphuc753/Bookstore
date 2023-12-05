package com.example.Bookstore.Presentation.Controller.Auth;

import com.example.Bookstore.Domain.Model.User.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSender {

    void send(String url, User theUser) throws MessagingException
            , UnsupportedEncodingException;
}
