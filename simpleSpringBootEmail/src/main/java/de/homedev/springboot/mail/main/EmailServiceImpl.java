package de.homedev.springboot.mail.main;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendMessage(String[] to, String[] cc, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        if (cc != null && cc.length > 0) {
            message.setCc(cc);
        }
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Override
    public void sendMessage(String[] to, String cc, String subject, String text) {
        Assert.notNull(cc, "cc is null");
        sendMessage(to, new String[] { cc }, subject, text);
    }

    @Override
    public void sendMessage(String to, String cc, String subject, String text) {
        Assert.notNull(to, "to is null");
        Assert.notNull(cc, "cc is null");
        sendMessage(new String[] { to }, new String[] { cc }, subject, text);
    }

    @Override
    public void sendMessage(String to, String subject, String text) {
        Assert.notNull(to, "to is null");
        sendMessage(new String[] { to }, (String[]) null, subject, text);
    }

    @Override
    public void sendMessage(String[] to, String[] cc, String subject, String text, InputStreamSource... is) throws MessagingException {
        if (is != null && is.length > 0) {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            if (cc != null && cc.length > 0) {
                helper.setCc(cc);
            }
            helper.setSubject(subject);
            helper.setText(text);
            for (int i = 0; i < is.length; i++) {
                helper.addAttachment(Integer.toString(i), is[i], "image/jpeg");
            }
            emailSender.send(message);
        } else {
            sendMessage(to, cc, subject, text);
        }
    }

    @Override
    public void sendMessage(String[] to, String cc, String subject, String text, InputStreamSource... is) throws MessagingException {
        Assert.notNull(cc, "cc is null");
        sendMessage(to, new String[] { cc }, subject, text, is);
    }

    @Override
    public void sendMessage(String to, String cc, String subject, String text, InputStreamSource... is) throws MessagingException {
        Assert.notNull(to, "to is null");
        Assert.notNull(cc, "cc is null");
        sendMessage(new String[] { to }, new String[] { cc }, subject, text, is);
    }

    @Override
    public void sendMessage(String to, String subject, String text, InputStreamSource... is) throws MessagingException {
        Assert.notNull(to, "to is null");
        sendMessage(new String[] { to }, (String[]) null, subject, text, is);
    }
}