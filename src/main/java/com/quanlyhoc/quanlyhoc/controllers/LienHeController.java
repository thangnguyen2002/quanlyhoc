package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.EmailFormDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.LienHeDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.services.EmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILienHeService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/lienhe")
@RequiredArgsConstructor
public class LienHeController {
    @Autowired
    private final ILienHeService iLienHeService;

    @Autowired
    private final EmailService emailService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaLienHe(@Valid @PathVariable("id") Long id) {
        try {
            iLienHeService.xoaLienHe(id);
            return new ResponseEntity<>(String.format("Liên hệ với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/guiEmailLienHe")
    public ResponseEntity<?> guiEmailLienHe(@RequestBody EmailFormDTO emailFormDTO) {
        try {
            emailService.guiEmail(emailFormDTO.getNguoiNhan(), emailFormDTO.getTieuDe(), emailFormDTO.getNoiDung()); //phai tim email ton tai ko...
            return new ResponseEntity<>("Gửi email thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filter-by-date")
    public ResponseEntity<?> filterByNgayLienHe(
        @RequestParam("ngayBatDau") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayBatDau,
        @RequestParam("ngayKetThuc") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKetThuc) {
        try {
            List<LienHe> lienHeList = iLienHeService.findByNgayLienHe(ngayBatDau, ngayKetThuc);
            return new ResponseEntity<>(lienHeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timLienHe(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<LienHe> lienHeList = iLienHeService.findByEmailOrHoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(lienHeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
