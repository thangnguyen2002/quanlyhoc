package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.models.LienHe;

import java.time.LocalDate;
import java.util.List;

public interface IEmailService {
    void guiEmail(String emailNhan, String tieuDe, String noiDung);
}
