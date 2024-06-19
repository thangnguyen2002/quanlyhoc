package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.responses.PhongHocListResponse;
import com.quanlyhoc.quanlyhoc.responses.TaiKhoanListResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITaiKhoanService;
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

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/taikhoan")
@RequiredArgsConstructor
public class TaiKhoanController {
    @Autowired
    private final ITaiKhoanService iTaiKhoanService;

    @PostMapping
    public ResponseEntity<?> themTaiKhoan(
            @Valid @RequestBody TaiKhoanDTO taiKhoanDTO
    ) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.themTaiKhoan(taiKhoanDTO);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaTaiKhoan(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody TaiKhoanDTO taiKhoanDTO) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.suaTaiKhoan(id, taiKhoanDTO);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaTaiKhoan(@Valid @PathVariable("id") Long id) {
        try {
            iTaiKhoanService.xoaTaiKhoan(id);
            return new ResponseEntity<>(String.format("Tài khoản với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timTaiKhoan(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<TaiKhoan> taiKhoanList = iTaiKhoanService.timTaiKhoanTheoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(taiKhoanList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTaiKhoan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maTaiKhoan") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<TaiKhoan> taiKhoanPage = iTaiKhoanService.findAll(pageable);
            int totalPages = taiKhoanPage.getTotalPages();
            List<TaiKhoan> taiKhoans = taiKhoanPage.getContent();
            return new ResponseEntity<>(TaiKhoanListResponse.builder()
                    .taiKhoanList(taiKhoans)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
