package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.PhongHocDTO;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPhongHocService {
    PhongHoc themPhongHoc(PhongHocDTO phongHocDTO) throws Exception;
//    PhongHoc getSubject(Long id) throws Exception;
    PhongHoc suaPhongHoc(Long id, PhongHocDTO phongHocDTO) throws Exception;
    void xoaPhongHoc(Long id) throws Exception;

    List<PhongHoc> findByTenPhongHoc(String TenPhongHoc) throws Exception;
    Page<PhongHoc> findAll(Pageable pageable) throws Exception;
}
