package com.quanlyhoc.quanlyhoc.repositories;

import com.quanlyhoc.quanlyhoc.models.BuoiHoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuoiHocRepository extends JpaRepository<BuoiHoc, Long> {
    List<BuoiHoc> findByTenBuoiHocContaining(String keyword);
}
