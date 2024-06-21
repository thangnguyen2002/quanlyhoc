package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IKhoaHocService {
    KhoaHoc themKhoaHoc(KhoaHocDTO khoaHocDTO) throws Exception;
    KhoaHoc suaKhoaHoc(Long id, KhoaHocDTO khoaHocDTO) throws Exception;
    void xoaKhoaHoc(Long id) throws Exception;

    List<KhoaHoc> findByTenKhoaHoc(String tenKhoaHoc) throws Exception;
    Page<KhoaHoc> findAll(Pageable pageable) throws Exception;

    KhoaHoc findById(Long id) throws Exception;
}
