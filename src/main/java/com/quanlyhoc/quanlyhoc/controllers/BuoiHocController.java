package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.BuoiHocDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.models.BuoiHoc;
import com.quanlyhoc.quanlyhoc.models.Module;
import com.quanlyhoc.quanlyhoc.responses.BuoiHocListResponse;
import com.quanlyhoc.quanlyhoc.responses.ModuleListResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBuoiHocService;
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
@RequestMapping("${api.prefix}/buoihoc")
@RequiredArgsConstructor
public class BuoiHocController {
    @Autowired
    private final IBuoiHocService iBuoiHocService;

    @PostMapping
    public ResponseEntity<?> themBuoiHoc(
            @Valid @RequestBody BuoiHocDTO buoiHocDTO
    ) {
        try {
            BuoiHoc buoiHoc = iBuoiHocService.themBuoiHoc(buoiHocDTO);
            return new ResponseEntity<>(buoiHoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaBuoiHoc(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody BuoiHocDTO buoiHocDTO) {
        try {
            BuoiHoc buoiHoc = iBuoiHocService.suaBuoiHoc(id, buoiHocDTO);
            return new ResponseEntity<>(buoiHoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaBuoiHoc(@Valid @PathVariable("id") Long id) {
        try {
            iBuoiHocService.xoaBuoiHoc(id);
            return new ResponseEntity<>(String.format("Buổi học với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timBuoiHoc(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<BuoiHoc> buoiHocList = iBuoiHocService.timBuoiHocTheoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(buoiHocList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBuoiHoc(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maBuoiHoc") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<BuoiHoc> buoiHocPage = iBuoiHocService.getAllBuoiHoc(pageable);
            int totalPages = buoiHocPage.getTotalPages();
            List<BuoiHoc> buoiHocList = buoiHocPage.getContent();
            return new ResponseEntity<>(BuoiHocListResponse.builder()
                    .buoiHocList(buoiHocList)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBuoiHocById(@Valid @PathVariable("id") Long id) {
        try {
            BuoiHoc buoiHoc = iBuoiHocService.timBuoiHocTheoId(id);
            return new ResponseEntity<>(buoiHoc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
