package com.quanlyhoc.quanlyhoc.services.interfaces;

import com.quanlyhoc.quanlyhoc.dtos.BaiVietDTO;
import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBaiVietService {
    BaiViet themBaiViet(BaiVietDTO baiVietDTO, MultipartFile file) throws Exception;
    BaiViet suaBaiViet(Long id, BaiVietDTO baiVietDTO, MultipartFile file) throws Exception;
    void xoaBaiViet(Long id) throws Exception;

    List<BaiViet> findByMaBaiVietOrTieuDe(String keyword) throws Exception;
}
