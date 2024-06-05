package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
//    @Query("SELECT s FROM Subject s WHERE " +
//            "(:teacherId IS NULL OR : teacherId = 0 OR s.user.id = :teacherId) " +
//            "AND (:keyword IS NULL OR :keyword = '' OR s.subjectName LIKE %:keyword%)")
//    Page<Subject> searchSubjects(Long teacherId, String keyword, Pageable pageable);
}
