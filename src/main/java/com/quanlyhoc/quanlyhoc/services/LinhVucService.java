package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.dtos.PhongHocDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.repositories.LinhVucRepository;
import com.quanlyhoc.quanlyhoc.repositories.PhongHocRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import com.quanlyhoc.quanlyhoc.services.interfaces.IPhongHocService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinhVucService implements ILinhVucService {
    @Autowired
    private final LinhVucRepository linhVucRepository;
    @Transactional
    @Override
    public LinhVuc themLinhVuc(LinhVucDTO linhVucDTO) throws Exception {
        LinhVuc linhVuc = LinhVuc.builder()
                .tenLinhVuc(linhVucDTO.getTenLinhVuc())
                .build();

        return linhVucRepository.save(linhVuc);
    }

    @Transactional
    @Override
    public LinhVuc suaLinhVuc(Long id, LinhVucDTO linhVucDTO) throws Exception {
        LinhVuc exLinhVuc = linhVucRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + id));
        exLinhVuc.setTenLinhVuc(linhVucDTO.getTenLinhVuc());

        return linhVucRepository.save(exLinhVuc);
    }

    @Transactional
    @Override
    public void xoaPLinhVuc(Long id) throws Exception {
        Optional<LinhVuc> exLinhVuc = linhVucRepository.findById(id);
        exLinhVuc.ifPresent(linhVucRepository::delete);
    }

    @Transactional
    @Override
    public List<LinhVuc> findByTenLinhVuc(String tenLinhVuc) throws Exception {
        return linhVucRepository.findByName(tenLinhVuc);
    }
}
