package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.dtos.PhongHocDTO;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import com.quanlyhoc.quanlyhoc.services.interfaces.IPhongHocService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/linhvuc")
@RequiredArgsConstructor
public class LinhVucController {
    @Autowired
    private final ILinhVucService iLinhVucService;

    @PostMapping
    public ResponseEntity<?> themLinhVuc(
            @Valid @RequestBody LinhVucDTO linhVucDTO,
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
            LinhVuc linhVuc = iLinhVucService.themLinhVuc(linhVucDTO);
            return new ResponseEntity<>(linhVuc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timPhonghoc(@RequestBody KeywordDTO keywordDTO) {

        try {
            List<LinhVuc> linhVucList = iLinhVucService.findByTenLinhVuc(keywordDTO.getKeyword());
            return new ResponseEntity<>(linhVucList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getSubject(@Valid @PathVariable("id") Long subjectId) {
//        try {
//            Subject subject = iSubjectService.getSubject(subjectId);
//            return new ResponseEntity<>(subject, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping
//    public ResponseEntity<SubjectListResponse> getSubjects(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
//            @RequestParam(value = "keyword", defaultValue = "") String keyword,
//            @RequestParam(value = "teacher_id", defaultValue = "0") Long teacherId
//
//    ) {
//        PageRequest pageRequest = PageRequest.of(
//                page, limit,
//                Sort.by("id").ascending()
//        );
//
//        Page<SubjectResponse> subjectPage = iSubjectService.getAllSubjects(teacherId, keyword, pageRequest);
//        // Lấy tổng số trang
//        int totalPages = subjectPage.getTotalPages();
//        List<SubjectResponse> subjects = subjectPage.getContent();
//
//        return new ResponseEntity<>(SubjectListResponse.builder()
//                .subjects(subjects)
//                .totalPages(totalPages)
//                .build(), HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaLinhVuc(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody LinhVucDTO linhVucDTO) {
        try {
            LinhVuc linhVuc = iLinhVucService.suaLinhVuc(id, linhVucDTO);
            return new ResponseEntity<>(linhVuc, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaLinhVuc(@Valid @PathVariable("id") Long id) {
        try {
            iLinhVucService.xoaPLinhVuc(id);
            return new ResponseEntity<>(String.format("Lĩnh vực với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
