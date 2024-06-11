package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {
@Query("SELECT k FROM ChucVu k WHERE k.tenChucVu LIKE %:tenChucVu%")
List<ChucVu> findByName(String tenChucVu);
}
