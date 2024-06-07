package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;

import java.util.List;

public interface IKhoaHocService {
    KhoaHoc themKhoaHoc(KhoaHocDTO khoaHocDTO) throws Exception;
    KhoaHoc suaKhoaHoc(Long id, KhoaHocDTO khoaHocDTO) throws Exception;
    void xoaKhoaHoc(Long id) throws Exception;

    List<KhoaHoc> findByTenKhoaHoc(String tenKhoaHoc) throws Exception;
}
