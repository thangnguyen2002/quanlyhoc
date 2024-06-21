package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.BaiVietDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import com.quanlyhoc.quanlyhoc.repositories.BaiVietRepository;
import com.quanlyhoc.quanlyhoc.repositories.NhanVienRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBaiVietService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaiVietService implements IBaiVietService {

    @Autowired
    private final BaiVietRepository baiVietRepository;

    @Autowired
    private final NhanVienRepository nhanVienRepository;

    @Autowired
    private final FileService fileService;

    @Transactional
    @Override
    public BaiViet themBaiViet(BaiVietDTO baiVietDTO, MultipartFile file) throws Exception {
        String fileUrl = fileService.saveFile(file);

        Long maNhanvien = baiVietDTO.getNguoiVietBai();
        NhanVien exNhanVien = nhanVienRepository.findById(maNhanvien)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhân viên với mã: " + maNhanvien));

        BaiViet baiViet = BaiViet.builder()
                .lanCapNhatCuoiCung(LocalDate.now())
                .ngayDang(LocalDate.now())
                .nhanVien(exNhanVien)
                .noiDung(baiVietDTO.getNoiDung())
                .tieuDe(baiVietDTO.getTieuDe())
                .trangThai(baiVietDTO.getTrangThai())
                .urlHinhAnhMinhHoa(fileUrl)
                .noiDungTomTat(baiVietDTO.getNoiDungTomTat())
                .build();

        return baiVietRepository.save(baiViet);
    }

    @Transactional
    @Override
    public BaiViet suaBaiViet(Long id, BaiVietDTO baiVietDTO, MultipartFile file) throws Exception {
        BaiViet exBaiViet = baiVietRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy bài viết với mã: " + id));

        Long maNhanvien = baiVietDTO.getNguoiVietBai();
        NhanVien exNhanVien = nhanVienRepository.findById(maNhanvien)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhân viên với mã: " + maNhanvien));

        exBaiViet.setLanCapNhatCuoiCung(LocalDate.now());
        exBaiViet.setNhanVien(exNhanVien);
        exBaiViet.setNoiDung(baiVietDTO.getNoiDung());
        exBaiViet.setTieuDe(baiVietDTO.getTieuDe());
        exBaiViet.setTrangThai(baiVietDTO.getTrangThai());
        exBaiViet.setNoiDungTomTat(baiVietDTO.getNoiDungTomTat());

        if (file != null && !file.isEmpty()) {
            String fileUrl = fileService.saveFile(file);
            exBaiViet.setUrlHinhAnhMinhHoa(fileUrl);
        }

        return baiVietRepository.save(exBaiViet);
    }
    @Transactional
    @Override
    public void xoaBaiViet(Long id) throws Exception {
        Optional<BaiViet> exBaiViet = baiVietRepository.findById(id);
        exBaiViet.ifPresent(baiVietRepository::delete);
    }
    @Transactional
    @Override
    public List<BaiViet> findByTieuDe(String keyword) throws Exception {
        return baiVietRepository.findByTieuDe(keyword);
    }
    @Transactional
    @Override
    public List<BaiViet> findByMaBaiViet(Long keyword) throws Exception {
        return baiVietRepository.findByMaBaiViet(keyword);
    }

    @Transactional
    @Override
    public Page<BaiViet> findAll(Pageable pageable) throws Exception {
        return baiVietRepository.findAll(pageable);
    }

    @Override
    public BaiViet findById(Long id) throws Exception {
        return baiVietRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy bài viết với mã: " + id));
    }
}
