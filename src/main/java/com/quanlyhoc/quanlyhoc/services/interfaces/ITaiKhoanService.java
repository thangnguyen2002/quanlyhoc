package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITaiKhoanService {
    TaiKhoan themTaiKhoan(TaiKhoanDTO taiKhoanDTO) throws Exception;
    TaiKhoan suaTaiKhoan(Long id, TaiKhoanDTO taiKhoanDTO) throws Exception;
    void xoaTaiKhoan(Long id) throws Exception;
    List<TaiKhoan> timTaiKhoanTheoTen(String tenTaiKhoan) throws Exception;
    Page<TaiKhoan> findAll(Pageable pageable) throws Exception;
    TaiKhoan findById(Long id) throws Exception;
}
