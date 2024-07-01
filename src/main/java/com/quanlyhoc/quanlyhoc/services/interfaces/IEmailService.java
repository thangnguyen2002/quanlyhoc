package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.models.LienHe;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface IEmailService {
    void sendMultipleEmails(List<String> emailsNhan, String tieuDe, String noiDung);
}
