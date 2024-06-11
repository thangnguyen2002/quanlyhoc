package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;

import java.util.List;

public interface ITaiKhoanService {
    TaiKhoan themTaiKhoan(TaiKhoanDTO taiKhoanDTO) throws Exception;
    TaiKhoan suaTaiKhoan(Long id, TaiKhoanDTO taiKhoanDTO) throws Exception;
    void xoaTaiKhoan(Long id) throws Exception;
    List<TaiKhoan> timTaiKhoanTheoTen(String tenTaiKhoan) throws Exception;
}
