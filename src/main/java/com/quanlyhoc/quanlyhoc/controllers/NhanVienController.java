package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.EmailFormDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.NhanVienDTO;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import com.quanlyhoc.quanlyhoc.responses.LienHeListResponse;
import com.quanlyhoc.quanlyhoc.responses.NhanVienListResponse;
import com.quanlyhoc.quanlyhoc.services.EmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.INhanVienService;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/nhanvien")
@RequiredArgsConstructor
public class NhanVienController {

    @Autowired
    private final INhanVienService iNhanVienService;

    @Autowired
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> themNhanVien(
            @Valid @RequestPart("nhanVienDTO") NhanVienDTO nhanVienDTO,
            @RequestPart("file") MultipartFile file) {
        try {
            NhanVien nhanVien = iNhanVienService.themNhanVien(nhanVienDTO, file);
            return new ResponseEntity<>(nhanVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaNhanVien(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestPart("nhanVienDTO") NhanVienDTO nhanVienDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            NhanVien nhanVien = iNhanVienService.suaNhanVien(id, nhanVienDTO, file);
            return new ResponseEntity<>(nhanVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaNhanVien(@Valid @PathVariable("id") Long id) {
        try {
            iNhanVienService.xoaNhanVien(id);
            return new ResponseEntity<>(String.format("Nhân viên với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timNhanVien(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<NhanVien> nhanVienList = iNhanVienService.timNhanVien(keywordDTO.getKeyword());
            return new ResponseEntity<>(nhanVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/export")
    public void exportDanhSachNhanVien(HttpServletResponse response) {
        try {
            List<NhanVien> nhanVienList = iNhanVienService.timNhanVien("");
            byte[] bytes = iNhanVienService.exportNhanVienToExcel(nhanVienList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=nhanvien.xlsx");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/exportByChucVu")
    public void exportNhanVienTheoChucVu(@RequestBody KeywordDTO keywordDTO, HttpServletResponse response) {
        try {
            List<NhanVien> nhanVienList = iNhanVienService.findByChucVu(keywordDTO.getKeyword());
            byte[] bytes = iNhanVienService.exportNhanVienToExcel(nhanVienList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=nhanvien_filtered.xlsx");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/guiEmailNhanVien")
    public ResponseEntity<?> guiEmailNhanVien(@RequestBody EmailFormDTO emailFormDTO) {
        try {
            emailService.guiEmail(emailFormDTO.getNguoiNhan(), emailFormDTO.getTieuDe(), emailFormDTO.getNoiDung()); //phai tim email ton tai ko...
            return new ResponseEntity<>("Gửi email thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filter-by-chucvu")
    public ResponseEntity<?> filterByChucVu(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<NhanVien> nhanVienList = iNhanVienService.findByChucVu(keywordDTO.getKeyword());
            return new ResponseEntity<>(nhanVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllNhanVien(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maNhanVien") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<NhanVien> nhanVienPage = iNhanVienService.findAll(pageable);
            int totalPages = nhanVienPage.getTotalPages();
            List<NhanVien> nhanViens = nhanVienPage.getContent();
            return new ResponseEntity<>(NhanVienListResponse.builder()
                    .nhanVienList(nhanViens)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
