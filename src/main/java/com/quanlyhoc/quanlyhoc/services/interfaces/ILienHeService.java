package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;

import java.time.LocalDate;
import java.util.List;

public interface ILienHeService {
    void xoaLienHe(Long id) throws Exception;
    List<LienHe> findByNgayLienHe(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    List<LienHe> findByEmailOrHoTen(String keyword) throws Exception;
}
