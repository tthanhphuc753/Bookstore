package com.example.SalesManagment.Controller.Aut;

import com.example.SalesManagment.Model.User.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface EmailSender {

    void send(String url, User theUser) throws MessagingException
            , UnsupportedEncodingException;
}
