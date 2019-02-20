package de.homedev.springboot.mail.main;

import javax.mail.MessagingException;

import org.springframework.core.io.InputStreamSource;

public interface EmailService {

    public void sendMessage(String[] to, String[] cc, String subject, String text);

    public void sendMessage(String[] to, String cc, String subject, String text);

    public void sendMessage(String to, String cc, String subject, String text);

    public void sendMessage(String to, String subject, String text);

    public void sendMessage(String[] to, String[] cc, String subject, String text, InputStreamSource... is) throws MessagingException;

    public void sendMessage(String[] to, String cc, String subject, String text, InputStreamSource... is) throws MessagingException;

    public void sendMessage(String to, String cc, String subject, String text, InputStreamSource... is) throws MessagingException;

    public void sendMessage(String to, String subject, String text, InputStreamSource... is) throws MessagingException;
}
