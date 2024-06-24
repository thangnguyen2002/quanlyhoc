package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.GiangVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IGiangVienService {
    GiangVien suaGiangVien(Long id, GiangVienDTO giangVienDTO, MultipartFile file) throws Exception;
    void xoaGiangVien(Long id) throws Exception;
    List<GiangVien> timGiangVien(String keyword) throws Exception;
    List<GiangVien> findByLinhVuc(String tenLinhVuc) throws Exception;
    byte[] exportGiangVienToExcel(List<GiangVien> giangVienList) throws Exception;

    Page<GiangVien> findAll(Pageable pageable) throws Exception;
    GiangVien findById(Long id) throws Exception;
}
