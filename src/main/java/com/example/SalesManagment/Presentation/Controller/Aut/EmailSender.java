package com.example.SalesManagment.Presentation.Controller.Aut;

import com.example.SalesManagment.Domain.Model.User.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSender {

    void send(String url, User theUser) throws MessagingException
            , UnsupportedEncodingException;
}
