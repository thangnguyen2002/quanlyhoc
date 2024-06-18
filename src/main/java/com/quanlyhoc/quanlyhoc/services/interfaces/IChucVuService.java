package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.ChucVuDTO;
import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;

import java.util.List;

public interface IChucVuService {
    ChucVu themChucVu(ChucVuDTO chucVuDTO) throws Exception;
    ChucVu suaChucVu(Long id, ChucVuDTO chucVuDTO) throws Exception;
    void xoaChucVu(Long id) throws Exception;
    List<ChucVu> timChucVuTheoTen(String tenChucVu) throws Exception;
    List<ChucVu> getAllChucVu() throws Exception;
}
