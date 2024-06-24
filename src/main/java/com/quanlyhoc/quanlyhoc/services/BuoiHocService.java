package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.BuoiHocDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.BuoiHoc;
import com.quanlyhoc.quanlyhoc.models.Module;
import com.quanlyhoc.quanlyhoc.repositories.BuoiHocRepository;
import com.quanlyhoc.quanlyhoc.repositories.ModuleRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBuoiHocService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuoiHocService implements IBuoiHocService {

    @Autowired
    private final BuoiHocRepository buoiHocRepository;

    @Autowired
    private final ModuleRepository moduleRepository;

    @Transactional
    @Override
    public BuoiHoc themBuoiHoc(BuoiHocDTO buoiHocDTO) throws Exception {
        Module module = moduleRepository.findById(buoiHocDTO.getMaModule())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy module với mã: " + buoiHocDTO.getMaModule()));

        BuoiHoc buoiHoc = BuoiHoc.builder()
                .tenBuoiHoc(buoiHocDTO.getTenBuoiHoc())
                .noiDungBuoiHoc(buoiHocDTO.getNoiDungBuoiHoc())
                .module(module)
                .build();

        return buoiHocRepository.save(buoiHoc);
    }

    @Transactional
    @Override
    public BuoiHoc suaBuoiHoc(Long id, BuoiHocDTO buoiHocDTO) throws Exception {
        BuoiHoc exBuoiHoc = buoiHocRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy buổi học với mã: " + id));

        Module module = moduleRepository.findById(buoiHocDTO.getMaModule())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy module với mã: " + buoiHocDTO.getMaModule()));

        exBuoiHoc.setTenBuoiHoc(buoiHocDTO.getTenBuoiHoc());
        exBuoiHoc.setNoiDungBuoiHoc(buoiHocDTO.getNoiDungBuoiHoc());
        exBuoiHoc.setModule(module);

        return buoiHocRepository.save(exBuoiHoc);
    }

    @Transactional
    @Override
    public void xoaBuoiHoc(Long id) throws Exception {
        BuoiHoc exBuoiHoc = buoiHocRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy buổi học với mã: " + id));
        buoiHocRepository.delete(exBuoiHoc);
    }

    @Transactional
    @Override
    public List<BuoiHoc> timBuoiHocTheoTen(String tenBuoiHoc) throws Exception {
        return buoiHocRepository.findByTenBuoiHocContaining(tenBuoiHoc);
    }

    @Override
    public Page<BuoiHoc> getAllBuoiHoc(Pageable pageable) throws Exception {
        return buoiHocRepository.findAll(pageable);
    }

    @Override
    public BuoiHoc timBuoiHocTheoId(Long id) throws Exception {
        return buoiHocRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy buổi học với mã: " + id));
    }
}
