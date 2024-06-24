package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.BuoiHocDTO;
import com.quanlyhoc.quanlyhoc.models.BuoiHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBuoiHocService {
    BuoiHoc themBuoiHoc(BuoiHocDTO buoiHocDTO) throws Exception;
    BuoiHoc suaBuoiHoc(Long id, BuoiHocDTO buoiHocDTO) throws Exception;
    void xoaBuoiHoc(Long id) throws Exception;
    List<BuoiHoc> timBuoiHocTheoTen(String tenBuoiHoc) throws Exception;
    Page<BuoiHoc> getAllBuoiHoc(Pageable pageable) throws Exception;
    BuoiHoc timBuoiHocTheoId(Long id) throws Exception;
}
