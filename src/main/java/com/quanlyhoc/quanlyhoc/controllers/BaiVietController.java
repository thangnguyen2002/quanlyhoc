package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.BaiVietDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBaiVietService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/baiviet")
@RequiredArgsConstructor
public class BaiVietController {
    @Autowired
    private final IBaiVietService iBaiVietService;

    @PostMapping
    public ResponseEntity<?> themBaiViet(
            @Valid @RequestPart("baiVietDTO") BaiVietDTO baiVietDTO,
            @RequestPart("file") MultipartFile file,
            BindingResult result
    ) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
            }
            BaiViet baiViet = iBaiVietService.themBaiViet(baiVietDTO, file);
            return new ResponseEntity<>(baiViet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timBaiViet(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<BaiViet> baiVietList;
            boolean isNumeric = isNumeric(keywordDTO.getKeyword());
            if (isNumeric) {
                baiVietList = iBaiVietService.findByMaBaiViet(Long.valueOf(keywordDTO.getKeyword()));
            } else {
                baiVietList = iBaiVietService.findByTieuDe(keywordDTO.getKeyword());
            }
            return new ResponseEntity<>(baiVietList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaBaiViet(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestPart("baiVietDTO") BaiVietDTO baiVietDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            BaiViet baiViet = iBaiVietService.suaBaiViet(id, baiVietDTO, file);
            return new ResponseEntity<>(baiViet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaBaiViet(@Valid @PathVariable("id") Long id) {
        try {
            iBaiVietService.xoaBaiViet(id);
            return new ResponseEntity<>(String.format("Bài viết với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

}
