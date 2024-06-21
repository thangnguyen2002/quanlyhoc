package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ILienHeService {
    void xoaLienHe(Long id) throws Exception;
    List<LienHe> findByNgayLienHe(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception;

    List<LienHe> findByEmailOrHoTen(String keyword) throws Exception;
    Page<LienHe> findAll(Pageable pageable) throws Exception;

    LienHe findById(Long id) throws Exception;
}
