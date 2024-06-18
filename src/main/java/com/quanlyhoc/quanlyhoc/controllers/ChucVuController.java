package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.ChucVuDTO;
import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.services.interfaces.IChucVuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/chucvu")
@RequiredArgsConstructor
public class ChucVuController {
    @Autowired
    private final IChucVuService iChucVuService;

    @PostMapping
    public ResponseEntity<?> themChucVu(
            @Valid @RequestBody ChucVuDTO chucVuDTO
    ) {
        try {
            ChucVu chucVu = iChucVuService.themChucVu(chucVuDTO);
            return new ResponseEntity<>(chucVu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaChucVu(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody ChucVuDTO chucVuDTO) {
        try {
            ChucVu chucVu = iChucVuService.suaChucVu(id, chucVuDTO);
            return new ResponseEntity<>(chucVu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaChucVu(@Valid @PathVariable("id") Long id) {
        try {
            iChucVuService.xoaChucVu(id);
            return new ResponseEntity<>(String.format("Chức vụ với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timChucVu(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<ChucVu> chucVuList = iChucVuService.timChucVuTheoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(chucVuList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllChucVu() {
        try {
            List<ChucVu> chucVuList = iChucVuService.getAllChucVu();
            return new ResponseEntity<>(chucVuList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
