package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.EmailFormDTO;
import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import com.quanlyhoc.quanlyhoc.responses.HocVienListResponse;
import com.quanlyhoc.quanlyhoc.services.EmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.IHocVienService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/hocvien")
@RequiredArgsConstructor
public class HocVienController {

    @Autowired
    private final IHocVienService iHocVienService;

    @Autowired
    private final EmailService emailService;

    @PutMapping("/{id}")
    public ResponseEntity<?> suaHocVien(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestPart("hocVienDTO") HocVienDTO hocVienDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            HocVien hocVien = iHocVienService.suaHocVien(id, hocVienDTO, file);
            return new ResponseEntity<>(hocVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaHocVien(@Valid @PathVariable("id") Long id) {
        try {
            iHocVienService.xoaHocVien(id);
            return new ResponseEntity<>(String.format("Học viên với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timHocVien(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<HocVien> hocVienList = iHocVienService.timHocVien(keywordDTO.getKeyword());
            return new ResponseEntity<>(hocVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/export")
    public void exportDanhSachHocVien(HttpServletResponse response) {
        try {
            List<HocVien> hocVienList = iHocVienService.timHocVien("");
            byte[] bytes = iHocVienService.exportHocVienToExcel(hocVienList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=hocvien.xlsx");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/guiEmailHocVien")
    public ResponseEntity<?> guiEmailHocVien(
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

    @PostMapping("/filter-by-tinhTrangHocTap")
    public ResponseEntity<?> filterByTinhTrangHocTap(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<HocVien> hocVienList = iHocVienService.findByTinhTrangHocTap(keywordDTO.getKeyword());
            return new ResponseEntity<>(hocVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllHocVien(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maHocVien") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<HocVien> hocVienPage = iHocVienService.findAll(pageable);
            int totalPages = hocVienPage.getTotalPages();
            List<HocVien> hocVienList = hocVienPage.getContent();
            return new ResponseEntity<>(HocVienListResponse.builder()
                    .hocVienList(hocVienList)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHocVienById(@Valid @PathVariable("id") Long id) {
        try {
            HocVien hocVien = iHocVienService.findById(id);
            return new ResponseEntity<>(hocVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
