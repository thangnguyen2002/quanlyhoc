package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.GiangVienDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.repositories.GiangVienRepository;
import com.quanlyhoc.quanlyhoc.repositories.LinhVucRepository;
import com.quanlyhoc.quanlyhoc.repositories.TaiKhoanRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IGiangVienService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiangVienService implements IGiangVienService {

    @Autowired
    private final GiangVienRepository giangVienRepository;

    @Autowired
    private final LinhVucRepository linhVucRepository;

    @Autowired
    private final TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private final FileService fileService;

    @Transactional
    @Override
    public GiangVien suaGiangVien(Long id, GiangVienDTO giangVienDTO, MultipartFile file) throws Exception {
        GiangVien exGiangVien = giangVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy giảng viên với mã: " + id));

        LinhVuc exLinhVuc = linhVucRepository.findById(giangVienDTO.getMaLinhVuc())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + giangVienDTO.getMaLinhVuc()));

        TaiKhoan exTaiKhoan = taiKhoanRepository.findById(giangVienDTO.getMaTaiKhoan())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + giangVienDTO.getMaTaiKhoan()));

        exGiangVien.setCoQuanCongTac(giangVienDTO.getCoQuanCongTac());
        exGiangVien.setDiaChi(giangVienDTO.getDiaChi());
        exGiangVien.setEmail(giangVienDTO.getEmail());
        exGiangVien.setGhiChu(giangVienDTO.getGhiChu());
        exGiangVien.setLinhVuc(exLinhVuc);
        exGiangVien.setNgaySinh(giangVienDTO.getNgaySinh());
        exGiangVien.setSoCmnd(giangVienDTO.getSoCmnd());
        exGiangVien.setSoDienThoai(giangVienDTO.getSoDienThoai());
        exGiangVien.setTenGiangVien(giangVienDTO.getTenGiangVien());
        exGiangVien.setTinhTrangCongTac(giangVienDTO.getTinhTrangCongTac());
        exGiangVien.setUrlHinhDaiDien(giangVienDTO.getUrlHinhDaiDien());
        exGiangVien.setGioiTinh(giangVienDTO.getGioiTinh());
        exGiangVien.setTaiKhoan(exTaiKhoan);

        if (file != null && !file.isEmpty()) {
            String fileUrl = fileService.saveFile(file);
            exGiangVien.setUrlHinhDaiDien(fileUrl);
        }

        return giangVienRepository.save(exGiangVien);
    }

    @Transactional
    @Override
    public void xoaGiangVien(Long id) throws Exception {
        GiangVien exGiangVien = giangVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy giảng viên với mã: " + id));
        giangVienRepository.delete(exGiangVien);
    }

    @Transactional
    @Override
    public List<GiangVien> timGiangVien(String keyword) throws Exception {
        return giangVienRepository.findByTenGiangVienContaining(keyword);
    }

    @Transactional
    @Override
    public List<GiangVien> findByLinhVuc(String tenLinhVuc) throws Exception {
        return giangVienRepository.findByTenLinhVuc(tenLinhVuc);
    }

    @Transactional
    @Override
    public byte[] exportGiangVienToExcel(List<GiangVien> giangVienList) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("GiangVien");
            Row headerRow = sheet.createRow(0);
            String[] columns = {
                    "Mã giảng viên",
                    "Tên giảng viên",
                    "Địa chỉ", "Email",
                    "Ghi chú",
                    "Mã lĩnh vực",
                    "Ngày sinh",
                    "Số CMND",
                    "Số điện thoại",
                    "URL hình đại diện",
                    "Giới tính",
                    "Cơ quan công tác",
                    "Tình trạng công tác"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (GiangVien giangVien : giangVienList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(giangVien.getMaGiangVien());
                row.createCell(1).setCellValue(giangVien.getTenGiangVien());
                row.createCell(2).setCellValue(giangVien.getDiaChi());
                row.createCell(3).setCellValue(giangVien.getEmail());
                row.createCell(4).setCellValue(giangVien.getGhiChu());
                row.createCell(5).setCellValue(giangVien.getLinhVuc().getMaLinhVuc());
                row.createCell(6).setCellValue(giangVien.getNgaySinh().toString());
                row.createCell(7).setCellValue(giangVien.getSoCmnd());
                row.createCell(8).setCellValue(giangVien.getSoDienThoai());
                row.createCell(9).setCellValue(giangVien.getUrlHinhDaiDien());
                row.createCell(10).setCellValue(giangVien.getGioiTinh());
                row.createCell(11).setCellValue(giangVien.getCoQuanCongTac());
                row.createCell(12).setCellValue(giangVien.getTinhTrangCongTac());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }

    @Transactional
    @Override
    public Page<GiangVien> findAll(Pageable pageable) throws Exception {
        return giangVienRepository.findAll(pageable);
    }

    @Override
    public GiangVien findById(Long id) throws Exception {
        return giangVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy giảng viên với mã: " + id));
    }
}
