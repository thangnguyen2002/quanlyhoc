package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KhoaHocRepository extends JpaRepository<KhoaHoc, Long> {
//    @Query("SELECT s FROM Subject s WHERE " +
//            "(:teacherId IS NULL OR : teacherId = 0 OR s.user.id = :teacherId) " +
//            "AND (:keyword IS NULL OR :keyword = '' OR s.subjectName LIKE %:keyword%)")
//    Page<Subject> searchSubjects(Long teacherId, String keyword, Pageable pageable);
@Query("SELECT k FROM KhoaHoc k WHERE k.tenKhoaHoc LIKE %:tenKhoaHoc%")
List<KhoaHoc> findByName(String tenKhoaHoc);
}
