package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubjectRepository  extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE " +
            "(:teacherId IS NULL OR : teacherId = 0 OR s.user.id = :teacherId) " +
            "AND (:keyword IS NULL OR :keyword = '' OR s.subjectName LIKE %:keyword%)")
    Page<Subject> searchSubjects(Long teacherId, String keyword, Pageable pageable);
}
