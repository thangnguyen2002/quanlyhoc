package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.KeywordDTO;
import com.quanlyhoc.quanlyhoc.dtos.ModuleDTO;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.Module;
import com.quanlyhoc.quanlyhoc.responses.LienHeListResponse;
import com.quanlyhoc.quanlyhoc.responses.ModuleListResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.IModuleService;
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
@RequestMapping("${api.prefix}/module")
@RequiredArgsConstructor
public class ModuleController {
    @Autowired
    private final IModuleService iModuleService;

    @PostMapping
    public ResponseEntity<?> themModule(
            @Valid @RequestBody ModuleDTO moduleDTO
    ) {
        try {
            Module module = iModuleService.themModule(moduleDTO);
            return new ResponseEntity<>(module, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaModule(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody ModuleDTO moduleDTO) {
        try {
            Module module = iModuleService.suaModule(id, moduleDTO);
            return new ResponseEntity<>(module, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaModule(@Valid @PathVariable("id") Long id) {
        try {
            iModuleService.xoaModule(id);
            return new ResponseEntity<>(String.format("Module với id = %d đã xoá thành công", id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timModule(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<Module> moduleList = iModuleService.timModuleTheoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(moduleList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllModule(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maModule") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<Module> modulePage = iModuleService.getAllModule(pageable);
            int totalPages = modulePage.getTotalPages();
            List<Module> moduleList = modulePage.getContent();
            return new ResponseEntity<>(ModuleListResponse.builder()
                    .moduleList(moduleList)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@Valid @PathVariable("id") Long id) {
        try {
            Module module = iModuleService.timModuleTheoId(id);
            return new ResponseEntity<>(module, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
