package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.dtos.TaiKhoan_GiangVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.TaiKhoan_HocVienDTO;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITaiKhoanService {
    TaiKhoan themTaiKhoanAdmin(TaiKhoanDTO taiKhoanDTO) throws Exception;
    TaiKhoan themTaiKhoanGiangVien(TaiKhoan_GiangVienDTO taiKhoanGiangVienDTO, MultipartFile file) throws Exception;
    TaiKhoan themTaiKhoanHocVien(TaiKhoan_HocVienDTO taiKhoanHocVienDTO, MultipartFile file) throws Exception;

    String dangNhap(String tenTaiKhoan, String matKhau) throws Exception;

    TaiKhoan getUserDetailsFromToken(String token) throws Exception;

    @Transactional
    void thayDoiMatKhau(String tenTaiKhoan, String matKhauCu, String matKhauMoi) throws Exception;

    TaiKhoan suaTaiKhoan(Long id, TaiKhoanDTO taiKhoanDTO) throws Exception;
    void xoaTaiKhoan(Long id) throws Exception;
    List<TaiKhoan> timTaiKhoanTheoTen(String tenTaiKhoan) throws Exception;
    Page<TaiKhoan> findAll(Pageable pageable) throws Exception;
    TaiKhoan findById(Long id) throws Exception;
}
