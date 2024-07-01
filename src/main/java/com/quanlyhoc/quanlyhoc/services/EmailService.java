package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.services.interfaces.IEmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Transactional
    @Override
    public void sendMultipleEmails(List<String> emailsNhan, String tieuDe, String noiDung) {
        for (String email : emailsNhan) {
            guiEmail(email, tieuDe, noiDung);
        }
    }
    public void guiEmail(String emailNhan, String tieuDe, String noiDung) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailNhan);
        message.setSubject(tieuDe);
        message.setText(noiDung);
        javaMailSender.send(message);
    }


}
