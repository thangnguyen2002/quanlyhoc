package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.ChucVuDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.repositories.ChucVuRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IChucVuService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChucVuService implements IChucVuService {
    @Autowired
    private final ChucVuRepository chucVuRepository;

    @Transactional
    @Override
    public ChucVu themChucVu(ChucVuDTO chucVuDTO) throws Exception {
        ChucVu chucVu = ChucVu.builder()
                .tenChucVu(chucVuDTO.getTenChucVu())
                .trangThai(chucVuDTO.getTrangThai())
                .build();

        return chucVuRepository.save(chucVu);
    }

    @Transactional
    @Override
    public ChucVu suaChucVu(Long id, ChucVuDTO chucVuDTO) throws Exception {
        ChucVu exChucVu = chucVuRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chức vụ với mã: " + id));

        exChucVu.setTenChucVu(chucVuDTO.getTenChucVu());
        exChucVu.setTrangThai(chucVuDTO.getTrangThai());

        return chucVuRepository.save(exChucVu);
    }

    @Transactional
    @Override
    public void xoaChucVu(Long id) throws Exception {
        Optional<ChucVu> exChucVu = chucVuRepository.findById(id);
        exChucVu.ifPresent(chucVuRepository::delete);
    }

    @Transactional
    @Override
    public List<ChucVu> timChucVuTheoTen(String tenChucVu) throws Exception {
        return chucVuRepository.findByName(tenChucVu);
    }

    @Override
    public List<ChucVu> getAllChucVu() throws Exception {
        return chucVuRepository.findAll();
    }

}
