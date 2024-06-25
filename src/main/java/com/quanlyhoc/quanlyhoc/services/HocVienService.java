package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.repositories.HocVienRepository;
import com.quanlyhoc.quanlyhoc.repositories.TaiKhoanRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IHocVienService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HocVienService implements IHocVienService {

    @Autowired
    private final HocVienRepository hocVienRepository;

    @Autowired
    private final TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private final FileService fileService;

    @Transactional
    @Override
    public HocVien suaHocVien(Long id, HocVienDTO hocVienDTO, MultipartFile file) throws Exception {
        HocVien exHocVien = hocVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy học viên với mã: " + id));

        TaiKhoan exTaiKhoan = taiKhoanRepository.findById(hocVienDTO.getMaTaiKhoan())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + hocVienDTO.getMaTaiKhoan()));

        exHocVien.setDiaChi(hocVienDTO.getDiaChi());
        exHocVien.setEmail(hocVienDTO.getEmail());
        exHocVien.setGhiChu(hocVienDTO.getGhiChu());
        exHocVien.setNgaySinh(hocVienDTO.getNgaySinh());
        exHocVien.setSoCmnd(hocVienDTO.getSoCmnd());
        exHocVien.setSoDienThoai(hocVienDTO.getSoDienThoai());
        exHocVien.setTenHocVien(hocVienDTO.getTenHocVien());
        exHocVien.setTinhTrangHocTap(hocVienDTO.getTinhTrangHocTap());
        exHocVien.setGioiTinh(hocVienDTO.getGioiTinh());
        exHocVien.setTaiKhoan(exTaiKhoan);

        // Kiểm tra nếu có file được upload
        if (file != null && !file.isEmpty()) {
            String fileUrl = fileService.saveFile(file);
            exHocVien.setUrlHinhDaiDien(fileUrl);
        }

        return hocVienRepository.save(exHocVien);
    }

    @Transactional
    @Override
    public void xoaHocVien(Long id) throws Exception {
        HocVien exHocVien = hocVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy học viên với mã: " + id));
        hocVienRepository.delete(exHocVien);
    }

    @Transactional
    @Override
    public List<HocVien> timHocVien(String keyword) throws Exception {
        return hocVienRepository.findByTenHocVienContaining(keyword);
    }

    @Transactional
    @Override
    public List<HocVien> findByTinhTrangHocTap(String tinhTrangHocTap) throws Exception {
        return hocVienRepository.findByTinhTrangHocTap(tinhTrangHocTap);
    }

    @Transactional
    @Override
    public byte[] exportHocVienToExcel(List<HocVien> hocVienList) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("HocVien");
            Row headerRow = sheet.createRow(0);
            String[] columns = {
                    "Mã học viên",
                    "Tên học viên",
                    "Địa chỉ",
                    "Email",
                    "Ghi chú",
                    "Ngày sinh",
                    "Số CMND",
                    "Số điện thoại",
                    "URL hình đại diện",
                    "Giới tính",
                    "Tình trạng học tập"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (HocVien hocVien : hocVienList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(hocVien.getMaHocVien());
                row.createCell(1).setCellValue(hocVien.getTenHocVien());
                row.createCell(2).setCellValue(hocVien.getDiaChi());
                row.createCell(3).setCellValue(hocVien.getEmail());
                row.createCell(4).setCellValue(hocVien.getGhiChu());
                row.createCell(5).setCellValue(hocVien.getNgaySinh().toString());
                row.createCell(6).setCellValue(hocVien.getSoCmnd());
                row.createCell(7).setCellValue(hocVien.getSoDienThoai());
                row.createCell(8).setCellValue(hocVien.getUrlHinhDaiDien());
                row.createCell(9).setCellValue(hocVien.getGioiTinh());
                row.createCell(10).setCellValue(hocVien.getTinhTrangHocTap());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }

    @Transactional
    @Override
    public Page<HocVien> findAll(Pageable pageable) throws Exception {
        return hocVienRepository.findAll(pageable);
    }

    @Override
    public HocVien findById(Long id) throws Exception {
        return hocVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy học viên với mã: " + id));
    }
}
