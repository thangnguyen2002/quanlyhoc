package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LienHeRepository extends JpaRepository<LienHe, Long> {
    @Query("SELECT c FROM LienHe c WHERE c.ngayLienHe BETWEEN :ngayBatDau AND :ngayKetThuc")
    List<LienHe> findAllByNgayLienHe(@Param("ngayBatDau") LocalDate ngayBatDau, @Param("ngayKetThuc") LocalDate ngayKetThuc);
    @Query("SELECT l FROM LienHe l WHERE l.email LIKE %:keyword% OR l.hoTen LIKE %:keyword%")
    List<LienHe> findByEmailOrHoTen(String keyword);
}
