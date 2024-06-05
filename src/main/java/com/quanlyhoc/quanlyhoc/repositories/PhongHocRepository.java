package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhongHocRepository  extends JpaRepository<PhongHoc, Long> {
//    @Query("SELECT s FROM Subject s WHERE " +
//            "(:teacherId IS NULL OR : teacherId = 0 OR s.user.id = :teacherId) " +
//            "AND (:keyword IS NULL OR :keyword = '' OR s.subjectName LIKE %:keyword%)")
//    Page<Subject> searchSubjects(Long teacherId, String keyword, Pageable pageable);

    @Query("SELECT p FROM PhongHoc p WHERE p.TenPhongHoc = :TenPhongHoc")
    List<PhongHoc> findByName(String TenPhongHoc);
}
