package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.BaiVietDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.repositories.BaiVietRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBaiVietService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaiVietService implements IBaiVietService {

    @Autowired
    private final BaiVietRepository baiVietRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public BaiViet themBaiViet(BaiVietDTO baiVietDTO, MultipartFile file) throws Exception {
        String fileName = saveFile(file);
        String fileUrl = "/uploads/" + fileName;

        BaiViet baiViet = BaiViet.builder()
                .lanCapNhatCuoiCung(baiVietDTO.getLanCapNhatCuoiCung())
                .maMenu(baiVietDTO.getMaMenu())
                .ngayDang(baiVietDTO.getNgayDang())
                .nguoiVietBai(baiVietDTO.getNguoiVietBai())
                .noiDung(baiVietDTO.getNoiDung())
                .soLuongTruyCap(baiVietDTO.getSoLuongTruyCap())
                .tieuDe(baiVietDTO.getTieuDe())
                .trangThai(baiVietDTO.getTrangThai())
                .urlHinhAnhMinhHoa(fileUrl)
                .noiDungTomTat(baiVietDTO.getNoiDungTomTat())
                .build();

        return baiVietRepository.save(baiViet);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, bytes);

        return file.getOriginalFilename();
    }

    @Override
    public BaiViet suaBaiViet(Long id, BaiVietDTO baiVietDTO, MultipartFile file) throws Exception {
        BaiViet exBaiViet = baiVietRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy bài viết với mã: " + id));

        exBaiViet.setLanCapNhatCuoiCung(baiVietDTO.getLanCapNhatCuoiCung());
        exBaiViet.setMaMenu(baiVietDTO.getMaMenu());
        exBaiViet.setNgayDang(baiVietDTO.getNgayDang());
        exBaiViet.setNguoiVietBai(baiVietDTO.getNguoiVietBai());
        exBaiViet.setNoiDung(baiVietDTO.getNoiDung());
        exBaiViet.setSoLuongTruyCap(baiVietDTO.getSoLuongTruyCap());
        exBaiViet.setTieuDe(baiVietDTO.getTieuDe());
        exBaiViet.setTrangThai(baiVietDTO.getTrangThai());
        exBaiViet.setNoiDungTomTat(baiVietDTO.getNoiDungTomTat());

        if (file != null && !file.isEmpty()) {
            String fileName = saveFile(file);
            String fileUrl = "/uploads/" + fileName;
            exBaiViet.setUrlHinhAnhMinhHoa(fileUrl);
        }

        return baiVietRepository.save(exBaiViet);
    }

    @Override
    public void xoaBaiViet(Long id) throws Exception {
        Optional<BaiViet> exBaiViet = baiVietRepository.findById(id);
        exBaiViet.ifPresent(baiVietRepository::delete);
    }

    @Override
    public List<BaiViet> findByMaBaiVietOrTieuDe(String keyword) throws Exception {
        return baiVietRepository.findByMaBaiVietOrTieuDe(keyword);
    }
}
