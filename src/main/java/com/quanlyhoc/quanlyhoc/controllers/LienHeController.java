package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.EmailFormDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.LienHeDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.responses.KhoaHocListResponse;
import com.quanlyhoc.quanlyhoc.responses.LienHeListResponse;
import com.quanlyhoc.quanlyhoc.services.EmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILienHeService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<?> guiEmailLienHe(
            @RequestParam List<String> emailsNhan,
            @RequestParam String tieuDe,
            @RequestParam String noiDung
    ) {
        try {
            emailService.sendMultipleEmails(emailsNhan, tieuDe, noiDung);
            return new ResponseEntity<>("Gửi emails thành công", HttpStatus.OK);
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

    @GetMapping
    public ResponseEntity<?> getAllLienHe(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ngayLienHe") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
            Page<LienHe> lienHePage = iLienHeService.findAll(pageable);
            int totalPages = lienHePage.getTotalPages();
            List<LienHe> lienHes = lienHePage.getContent();
            return new ResponseEntity<>(LienHeListResponse.builder()
                    .lienHeList(lienHes)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLienHeById(@Valid @PathVariable("id") Long id) {
        try {
            LienHe lienHe = iLienHeService.findById(id);
            return new ResponseEntity<>(lienHe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
