package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.BaiVietDTO;
import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBaiVietService {
    BaiViet themBaiViet(BaiVietDTO baiVietDTO, MultipartFile file) throws Exception;
    BaiViet suaBaiViet(Long id, BaiVietDTO baiVietDTO, MultipartFile file) throws Exception;
    void xoaBaiViet(Long id) throws Exception;
    List<BaiViet> findByTieuDe(String keyword) throws Exception;

    List<BaiViet> findByMaBaiViet(Long keyword) throws Exception;
    Page<BaiViet> findAll(Pageable pageable) throws Exception;
    BaiViet findById(Long id) throws Exception;
}
