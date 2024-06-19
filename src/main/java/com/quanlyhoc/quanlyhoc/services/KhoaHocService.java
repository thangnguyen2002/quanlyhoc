package com.quanlyhoc.quanlyhoc.services;
import com.quanlyhoc.quanlyhoc.dtos.KhoaHocDTO;
import com.quanlyhoc.quanlyhoc.dtos.LinhVucDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.repositories.KhoaHocRepository;
import com.quanlyhoc.quanlyhoc.repositories.LinhVucRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IKhoaHocService;
import com.quanlyhoc.quanlyhoc.services.interfaces.ILinhVucService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KhoaHocService implements IKhoaHocService {
    @Autowired
    private final KhoaHocRepository khoaHocRepository;

    @Autowired
    private final LinhVucRepository linhVucRepository;

    @Transactional
    @Override
    public KhoaHoc themKhoaHoc(KhoaHocDTO khoaHocDTO) throws Exception {
        Long maLinhVuc = khoaHocDTO.getMaLinhVuc();
        LinhVuc exLinhVuc = linhVucRepository.findById(maLinhVuc)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + maLinhVuc));

        KhoaHoc khoaHoc = KhoaHoc.builder()
                .hocPhi(khoaHocDTO.getHocPhi())
                .linhVuc(exLinhVuc)
                .soBuoi(khoaHocDTO.getSoBuoi())
                .tenKhoaHoc(khoaHocDTO.getTenKhoaHoc())
                .ghiChu(khoaHocDTO.getGhiChu())
                .noiDungKhoaHoc(khoaHocDTO.getNoiDungKhoaHoc())
                .noiDungTomTatKhoaHoc(khoaHocDTO.getNoiDungTomTatKhoaHoc())
                .build();

        return khoaHocRepository.save(khoaHoc);
    }
    @Transactional
    @Override
    public KhoaHoc suaKhoaHoc(Long id, KhoaHocDTO khoaHocDTO) throws Exception {
        KhoaHoc exKhoaHoc = khoaHocRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy khoá học với mã: " + id));

        Long maLinhVuc = khoaHocDTO.getMaLinhVuc();
        LinhVuc exLinhVuc = linhVucRepository.findById(maLinhVuc)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + maLinhVuc));

        exKhoaHoc.setHocPhi(khoaHocDTO.getHocPhi());
        exKhoaHoc.setLinhVuc(exLinhVuc);
        exKhoaHoc.setSoBuoi(khoaHocDTO.getSoBuoi());
        exKhoaHoc.setTenKhoaHoc(khoaHocDTO.getTenKhoaHoc());
        exKhoaHoc.setGhiChu(khoaHocDTO.getGhiChu());
        exKhoaHoc.setNoiDungKhoaHoc(khoaHocDTO.getNoiDungKhoaHoc());
        exKhoaHoc.setNoiDungTomTatKhoaHoc(khoaHocDTO.getNoiDungTomTatKhoaHoc());

        return khoaHocRepository.save(exKhoaHoc);
    }
    @Transactional
    @Override
    public void xoaKhoaHoc(Long id) throws Exception {
        Optional<KhoaHoc> exKhoaHoc = khoaHocRepository.findById(id);
        exKhoaHoc.ifPresent(khoaHocRepository::delete);
    }
    @Transactional
    @Override
    public List<KhoaHoc> findByTenKhoaHoc(String tenKhoaHoc) throws Exception {
        return khoaHocRepository.findByName(tenKhoaHoc);
    }

    @Transactional
    @Override
    public Page<KhoaHoc> findAll(Pageable pageable) throws Exception {
        return khoaHocRepository.findAll(pageable);
    }
}
