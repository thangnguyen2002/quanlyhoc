package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    List<TaiKhoan> findByTenTaiKhoanContaining(String tenTaiKhoan);
    TaiKhoan findByTenTaiKhoan(String tenTaiKhoan);
    boolean existsByTenTaiKhoan(String tenTaiKhoan);
}
