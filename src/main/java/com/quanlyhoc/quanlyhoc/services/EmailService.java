package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.repositories.LienHeRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IEmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILienHeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void guiEmail(String emailNhan, String tieuDe, String noiDung) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailNhan);
        message.setSubject(tieuDe);
        message.setText(noiDung);
        javaMailSender.send(message);
    }
}
