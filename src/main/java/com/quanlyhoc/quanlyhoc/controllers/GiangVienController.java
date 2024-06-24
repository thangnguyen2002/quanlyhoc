package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.EmailFormDTO;
import com.quanlyhoc.quanlyhoc.dtos.GiangVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.responses.GiangVienListResponse;
import com.quanlyhoc.quanlyhoc.services.EmailService;
import com.quanlyhoc.quanlyhoc.services.interfaces.IGiangVienService;
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
@RequestMapping("${api.prefix}/giangvien")
@RequiredArgsConstructor
public class GiangVienController {

    @Autowired
    private final IGiangVienService iGiangVienService;

    @Autowired
    private final EmailService emailService;

    @PutMapping("/{id}")
    public ResponseEntity<?> suaGiangVien(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestPart("giangVienDTO") GiangVienDTO giangVienDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            GiangVien giangVien = iGiangVienService.suaGiangVien(id, giangVienDTO, file);
            return new ResponseEntity<>(giangVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaGiangien(@Valid @PathVariable("id") Long id) {
        try {
            iGiangVienService.xoaGiangVien(id);
            return new ResponseEntity<>(String.format("Giảng viên với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timGiangVien(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<GiangVien> giangVienList = iGiangVienService.timGiangVien(keywordDTO.getKeyword());
            return new ResponseEntity<>(giangVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/export")
    public void exportDanhSachGiangVien(HttpServletResponse response) {
        try {
            List<GiangVien> giangVienList = iGiangVienService.timGiangVien("");
            byte[] bytes = iGiangVienService.exportGiangVienToExcel(giangVienList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=giangvien.xlsx");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/exportByLinhVuc")
    public void exportGiangVienTheoLinhVuc(@RequestBody KeywordDTO keywordDTO, HttpServletResponse response) {
        try {
            List<GiangVien> giangVienList = iGiangVienService.findByLinhVuc(keywordDTO.getKeyword());
            byte[] bytes = iGiangVienService.exportGiangVienToExcel(giangVienList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=giangvien_filtered.xlsx");
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
    }

    @PostMapping("/guiEmailGiangVien")
    public ResponseEntity<?> guiEmailGiangVien(@RequestBody EmailFormDTO emailFormDTO) {
        try {
            emailService.guiEmail(emailFormDTO.getNguoiNhan(), emailFormDTO.getTieuDe(), emailFormDTO.getNoiDung()); //phai tim email ton tai ko...
            return new ResponseEntity<>("Gửi email thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/filter-by-linhvuc")
    public ResponseEntity<?> filterByLinhVuc(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<GiangVien> giangVienList = iGiangVienService.findByLinhVuc(keywordDTO.getKeyword());
            return new ResponseEntity<>(giangVienList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllNhanVien(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maGiangVien") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<GiangVien> giangVienPage = iGiangVienService.findAll(pageable);
            int totalPages = giangVienPage.getTotalPages();
            List<GiangVien> giangVienList = giangVienPage.getContent();
            return new ResponseEntity<>(GiangVienListResponse.builder()
                    .giangVienList(giangVienList)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGiangVienById(@Valid @PathVariable("id") Long id) {
        try {
            GiangVien giangVien = iGiangVienService.findById(id);
            return new ResponseEntity<>(giangVien, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
