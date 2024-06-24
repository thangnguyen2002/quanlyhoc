package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("SELECT m FROM Module m WHERE m.tenModule LIKE %:tenModule%")
    List<Module> findByName(String tenModule);
}
