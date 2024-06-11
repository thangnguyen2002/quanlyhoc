package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    List<NhanVien> findByTenNhanVienContaining(String tenNhanVien);
    @Query("SELECT nv FROM NhanVien nv JOIN nv.chucVu cv WHERE cv.tenChucVu LIKE %:tenChucVu%")
    List<NhanVien> findByTenChucVu(@Param("tenChucVu") String tenChucVu);
}
