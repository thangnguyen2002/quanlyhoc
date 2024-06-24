package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocVienRepository extends JpaRepository<HocVien, Long> {
    List<HocVien> findByTenHocVienContaining(String tenHocVien);
    @Query("SELECT h FROM HocVien h WHERE h.tinhTrangHocTap = :tinhTrangHocTap")
    List<HocVien> findByTinhTrangHocTap(@Param("tinhTrangHocTap") String tinhTrangHocTap);
}
