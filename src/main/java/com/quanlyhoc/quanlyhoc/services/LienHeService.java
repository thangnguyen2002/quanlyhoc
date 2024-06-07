package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.repositories.LienHeRepository;
import com.quanlyhoc.quanlyhoc.repositories.LinhVucRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILienHeService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LienHeService implements ILienHeService {
    @Autowired
    private final LienHeRepository lienHeRepository;
    @Override
    public void xoaLienHe(Long id) throws Exception {
        Optional<LienHe> exLienHe = lienHeRepository.findById(id);
        exLienHe.ifPresent(lienHeRepository::delete);
    }

    @Override
    public List<LienHe> findByNgayLienHe(LocalDate ngayBatDau, LocalDate ngayKetThuc) throws Exception {
        return lienHeRepository.findAllByNgayLienHe(ngayBatDau,ngayKetThuc);
    }

    @Override
    public List<LienHe> findByEmailOrHoTen(String keyword) throws Exception {
        return lienHeRepository.findByEmailOrHoTen(keyword);
    }
}
