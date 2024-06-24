package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.ModuleDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.Module;
import com.quanlyhoc.quanlyhoc.repositories.KhoaHocRepository;
import com.quanlyhoc.quanlyhoc.repositories.ModuleRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IModuleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService implements IModuleService {

    @Autowired
    private final ModuleRepository moduleRepository;

    @Autowired
    private final KhoaHocRepository khoaHocRepository;

    @Transactional
    @Override
    public Module themModule(ModuleDTO moduleDTO) throws Exception {
        KhoaHoc khoaHoc = khoaHocRepository.findById(moduleDTO.getMaKhoaHoc())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy khóa học với mã: " + moduleDTO.getMaKhoaHoc()));

        Module module = Module.builder()
                .tenModule(moduleDTO.getTenModule())
                .moTa(moduleDTO.getMoTa())
                .khoaHoc(khoaHoc)
                .build();

        return moduleRepository.save(module);
    }

    @Transactional
    @Override
    public Module suaModule(Long id, ModuleDTO moduleDTO) throws Exception {
        Module exModule = moduleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy module với mã: " + id));

        KhoaHoc khoaHoc = khoaHocRepository.findById(moduleDTO.getMaKhoaHoc())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy khóa học với mã: " + moduleDTO.getMaKhoaHoc()));

        exModule.setTenModule(moduleDTO.getTenModule());
        exModule.setMoTa(moduleDTO.getMoTa());
        exModule.setKhoaHoc(khoaHoc);

        return moduleRepository.save(exModule);
    }

    @Transactional
    @Override
    public void xoaModule(Long id) throws Exception {
        Module exModule = moduleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy module với mã: " + id));
        moduleRepository.delete(exModule);
    }

    @Transactional
    @Override
    public List<Module> timModuleTheoTen(String tenModule) throws Exception {
        return moduleRepository.findByName(tenModule);
    }

    @Override
    public Page<Module> getAllModule(Pageable pageable) throws Exception {
        return moduleRepository.findAll(pageable);
    }

    @Override
    public Module timModuleTheoId(Long id) throws Exception {
        return moduleRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy module với mã: " + id));
    }
}
