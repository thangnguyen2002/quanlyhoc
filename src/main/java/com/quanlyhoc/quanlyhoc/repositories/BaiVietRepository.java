package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long> {
    @Query("SELECT b FROM BaiViet b WHERE b.tieuDe LIKE %:keyword%")
    List<BaiViet> findByTieuDe(String keyword);

    @Query("SELECT b FROM BaiViet b WHERE b.maBaiViet = :maBaiViet")
    List<BaiViet> findByMaBaiViet(Long maBaiViet);
}
