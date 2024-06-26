package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.responses.BaiVietListResponse;
import com.quanlyhoc.quanlyhoc.responses.KhoaHocListResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.IKhoaHocService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/khoahoc")
@RequiredArgsConstructor
public class KhoaHocController {
    @Autowired
    private final IKhoaHocService iKhoaHocService;

    @PostMapping
    public ResponseEntity<?> themKhoaHoc(
            @Valid @RequestBody KhoaHocDTO khoaHocDTO,
            BindingResult result
    ) {
        try {
            if(result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
            }
            KhoaHoc khoaHoc = iKhoaHocService.themKhoaHoc(khoaHocDTO);
            return new ResponseEntity<>(khoaHoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timKhoaHoc(@RequestBody KeywordDTO keywordDTO) {

        try {
            List<KhoaHoc> khoaHocList = iKhoaHocService.findByTenKhoaHoc(keywordDTO.getKeyword());
            return new ResponseEntity<>(khoaHocList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaKhoaHoc(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody KhoaHocDTO khoaHocDTO) {
        try {
            KhoaHoc khoaHoc = iKhoaHocService.suaKhoaHoc(id, khoaHocDTO);
            return new ResponseEntity<>(khoaHoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaKhoaHoc(@Valid @PathVariable("id") Long id) {
        try {
            iKhoaHocService.xoaKhoaHoc(id);
            return new ResponseEntity<>(String.format("Khoá học với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllKhoaHoc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maKhoaHoc") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<KhoaHoc> khoaHocPage = iKhoaHocService.findAll(pageable);
            int totalPages = khoaHocPage.getTotalPages();
            List<KhoaHoc> khoaHocs = khoaHocPage.getContent();
            return new ResponseEntity<>(KhoaHocListResponse.builder()
                    .khoaHocList(khoaHocs)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getKhoaHocById(@Valid @PathVariable("id") Long id) {
        try {
            KhoaHoc khoahoc = iKhoaHocService.findById(id);
            return new ResponseEntity<>(khoahoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
