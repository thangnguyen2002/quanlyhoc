package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHocVienService {
    HocVien suaHocVien(Long id, HocVienDTO hocVienDTO, MultipartFile file) throws Exception;
    void xoaHocVien(Long id) throws Exception;
    List<HocVien> timHocVien(String keyword) throws Exception;
    List<HocVien> findByTinhTrangHocTap(String tinhTrangHocTap) throws Exception;
    byte[] exportHocVienToExcel(List<HocVien> hocVienList) throws Exception;
    Page<HocVien> findAll(Pageable pageable) throws Exception;
    HocVien findById(Long id) throws Exception;
}
