package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.GiangVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiangVienRepository extends JpaRepository<GiangVien, Long> {
    List<GiangVien> findByTenGiangVienContaining(String tenGiangVien);
    @Query("SELECT gv FROM GiangVien gv JOIN gv.linhVuc lv WHERE lv.tenLinhVuc LIKE %:tenLinhVuc%")
    List<GiangVien> findByTenLinhVuc(@Param("tenLinhVuc") String tenLinhVuc);
}
