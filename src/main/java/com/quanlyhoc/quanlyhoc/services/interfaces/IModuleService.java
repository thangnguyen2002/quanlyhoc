package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.ModuleDTO;
import com.quanlyhoc.quanlyhoc.models.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IModuleService {
    Module themModule(ModuleDTO moduleDTO) throws Exception;
    Module suaModule(Long id, ModuleDTO moduleDTO) throws Exception;
    void xoaModule(Long id) throws Exception;
    List<Module> timModuleTheoTen(String tenModule) throws Exception;
    Page<Module> getAllModule(Pageable pageable) throws Exception;
    Module timModuleTheoId(Long id) throws Exception;
}
