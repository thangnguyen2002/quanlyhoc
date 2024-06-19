package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.NhanVienDTO;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface INhanVienService {
    NhanVien themNhanVien(NhanVienDTO nhanVienDTO, MultipartFile file) throws Exception;
    NhanVien suaNhanVien(Long id, NhanVienDTO nhanVienDTO, MultipartFile file) throws Exception;
    void xoaNhanVien(Long id) throws Exception;
    List<NhanVien> timNhanVien(String keyword) throws Exception;
    List<NhanVien> findByChucVu(String tenChucVu) throws Exception;
    byte[] exportNhanVienToExcel(List<NhanVien> nhanVienList) throws Exception;

    Page<NhanVien> findAll(Pageable pageable) throws Exception;
}
