package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.dtos.PhongHocDTO;
import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;

import java.util.List;

public interface ILinhVucService {
    LinhVuc themLinhVuc(LinhVucDTO linhVucDTO) throws Exception;
    LinhVuc suaLinhVuc(Long id, LinhVucDTO linhVucDTO) throws Exception;
    void xoaPLinhVuc(Long id) throws Exception;

    List<LinhVuc> findByTenLinhVuc(String tenLinhVuc) throws Exception;

    List<LinhVuc> getAllLinhVuc() throws Exception;
}
