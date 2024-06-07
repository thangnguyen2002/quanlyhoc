package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LinhVucRepository extends JpaRepository<LinhVuc, Long> {
    @Query("SELECT p FROM LinhVuc p WHERE p.tenLinhVuc LIKE %:tenLinhVuc%")
    List<LinhVuc> findByName(String tenLinhVuc);

}
