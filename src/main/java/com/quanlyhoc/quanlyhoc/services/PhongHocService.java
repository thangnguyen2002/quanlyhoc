package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.dtos.PhongHocDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.repositories.PhongHocRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IPhongHocService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhongHocService implements IPhongHocService {
    @Autowired
    private final PhongHocRepository phongHocRepository;
    @Override
    public PhongHoc themPhongHoc(PhongHocDTO phongHocDTO) throws Exception {
        PhongHoc phongHoc = PhongHoc.builder()
                .ghiChu(phongHocDTO.getGhiChu())
                .soChoNgoi(phongHocDTO.getSoChoNgoi())
                .TenPhongHoc(phongHocDTO.getTenPhongHoc())
                .build();

        return phongHocRepository.save(phongHoc);
    }

    @Override
    public PhongHoc suaPhongHoc(Long id, PhongHocDTO phongHocDTO) throws Exception {
        PhongHoc exPhongHoc = phongHocRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy phòng học với mã: " + id));
        exPhongHoc.setGhiChu(phongHocDTO.getGhiChu());
        exPhongHoc.setSoChoNgoi(phongHocDTO.getSoChoNgoi());
        exPhongHoc.setTenPhongHoc(phongHocDTO.getTenPhongHoc());

        return phongHocRepository.save(exPhongHoc);
    }

    @Override
    public void xoaPhongHoc(Long id) throws Exception {
        Optional<PhongHoc> exPhongHoc = phongHocRepository.findById(id);
        exPhongHoc.ifPresent(phongHocRepository::delete);
    }

    @Override
    public List<PhongHoc> findByTenPhongHoc(String TenPhongHoc) throws Exception {
        return phongHocRepository.findByName(TenPhongHoc);
    }
}
