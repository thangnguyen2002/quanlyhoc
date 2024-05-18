package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.SubjectDTO;
import com.quanlyhoc.quanlyhoc.models.Subject;
import com.quanlyhoc.quanlyhoc.responses.SubjectListResponse;
import com.quanlyhoc.quanlyhoc.responses.SubjectResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.ISubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/subjects")
@RequiredArgsConstructor
public class SubjectController {
    @Autowired
    private final ISubjectService iSubjectService;

    @PostMapping
    public ResponseEntity<?> createSubject(
            @Valid @RequestBody SubjectDTO subjectDTO,
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
            SubjectResponse subjectResponse = iSubjectService.createSubject(subjectDTO);
            return new ResponseEntity<>(subjectResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSubject(@Valid @PathVariable("id") Long subjectId) {
        try {
            Subject subject = iSubjectService.getSubject(subjectId);
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<SubjectListResponse> getSubjects(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "teacher_id", defaultValue = "0") Long teacherId

    ) {
        PageRequest pageRequest = PageRequest.of(
                page, limit,
                Sort.by("id").ascending()
        );

        Page<SubjectResponse> subjectPage = iSubjectService.getAllSubjects(teacherId, keyword, pageRequest);
        // Lấy tổng số trang
        int totalPages = subjectPage.getTotalPages();
        List<SubjectResponse> subjects = subjectPage.getContent();

        return new ResponseEntity<>(SubjectListResponse.builder()
                .subjects(subjects)
                .totalPages(totalPages)
                .build(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody SubjectDTO subjectDTO) {
        try {
            Subject subject = iSubjectService.updateSubject(id, subjectDTO);
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@Valid @PathVariable("id") Long id) {
        try {
            iSubjectService.deleteSubject(id);
            return new ResponseEntity<>(String.format("Subject with id = %d deleted successfully", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
