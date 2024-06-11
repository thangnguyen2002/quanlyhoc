package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.NhanVienDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.ChucVu;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import com.quanlyhoc.quanlyhoc.repositories.ChucVuRepository;
import com.quanlyhoc.quanlyhoc.repositories.NhanVienRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.IBaiVietService;
import com.quanlyhoc.quanlyhoc.services.interfaces.INhanVienService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NhanVienService implements INhanVienService {

    @Autowired
    private final NhanVienRepository nhanVienRepository;

    @Autowired
    private final ChucVuRepository chucVuRepository;

    @Autowired
    private final IBaiVietService iBaiVietService;

    @Autowired
    private final FileService fileService;

    @Transactional
    @Override
    public NhanVien themNhanVien(NhanVienDTO nhanVienDTO, MultipartFile file) throws Exception {
        String fileUrl = fileService.saveFile(file);

        ChucVu exChucVu = chucVuRepository.findById(nhanVienDTO.getMaChucVu())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chức vụ với mã: " + nhanVienDTO.getMaChucVu()));

        NhanVien nhanVien = NhanVien.builder()
                .diaChi(nhanVienDTO.getDiaChi())
                .email(nhanVienDTO.getEmail())
                .ghiChu(nhanVienDTO.getGhiChu())
                .chucVu(exChucVu)
                .ngaySinh(nhanVienDTO.getNgaySinh())
                .nguoiNhapThongTin(nhanVienDTO.getNguoiNhapThongTin())
                .soCmnd(nhanVienDTO.getSoCmnd())
                .soDienThoai(nhanVienDTO.getSoDienThoai())
                .tenNhanVien(nhanVienDTO.getTenNhanVien())
                .urlHinhDaiDien(fileUrl)
                .gioiTinh(nhanVienDTO.getGioiTinh())
                .build();

        return nhanVienRepository.save(nhanVien);
    }

    @Transactional
    @Override
    public NhanVien suaNhanVien(Long id, NhanVienDTO nhanVienDTO, MultipartFile file) throws Exception {
        NhanVien exNhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhân viên với mã: " + id));

        ChucVu exChucVu = chucVuRepository.findById(nhanVienDTO.getMaChucVu())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy chức vụ với mã: " + nhanVienDTO.getMaChucVu()));

        exNhanVien.setDiaChi(nhanVienDTO.getDiaChi());
        exNhanVien.setEmail(nhanVienDTO.getEmail());
        exNhanVien.setGhiChu(nhanVienDTO.getGhiChu());
        exNhanVien.setChucVu(exChucVu);
        exNhanVien.setNgaySinh(nhanVienDTO.getNgaySinh());
        exNhanVien.setNguoiNhapThongTin(nhanVienDTO.getNguoiNhapThongTin());
        exNhanVien.setSoCmnd(nhanVienDTO.getSoCmnd());
        exNhanVien.setSoDienThoai(nhanVienDTO.getSoDienThoai());
        exNhanVien.setTenNhanVien(nhanVienDTO.getTenNhanVien());
        exNhanVien.setUrlHinhDaiDien(nhanVienDTO.getUrlHinhDaiDien());
        exNhanVien.setGioiTinh(nhanVienDTO.getGioiTinh());

        if (file != null && !file.isEmpty()) {
            String fileUrl = fileService.saveFile(file);
            exNhanVien.setUrlHinhDaiDien(fileUrl);
        }

        return nhanVienRepository.save(exNhanVien);
    }

    @Transactional
    @Override
    public void xoaNhanVien(Long id) throws Exception {
        NhanVien exNhanVien = nhanVienRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhân viên với mã: " + id));
        nhanVienRepository.delete(exNhanVien);
    }

    @Transactional
    @Override
    public List<NhanVien> timNhanVien(String keyword) throws Exception {
        return nhanVienRepository.findByTenNhanVienContaining(keyword);
    }

    @Transactional
    @Override
    public List<NhanVien> findByChucVu(String tenChucVu) throws Exception {
        return nhanVienRepository.findByTenChucVu(tenChucVu);
    }

    @Override
    public byte[] exportNhanVienToExcel(List<NhanVien> nhanVienList) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("NhanVien");
            Row headerRow = sheet.createRow(0);
            String[] columns = {
                    "Mã nhân viên",
                    "Tên nhân viên",
                    "Địa chỉ", "Email",
                    "Ghi chú",
                    "Mã chức vụ",
                    "Ngày sinh",
                    "Người nhập thông tin",
                    "Số CMND",
                    "Số điện thoại",
                    "URL hình đại diện",
                    "Giới tính"
            };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (NhanVien nhanVien : nhanVienList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(nhanVien.getMaNhanVien());
                row.createCell(1).setCellValue(nhanVien.getTenNhanVien());
                row.createCell(2).setCellValue(nhanVien.getDiaChi());
                row.createCell(3).setCellValue(nhanVien.getEmail());
                row.createCell(4).setCellValue(nhanVien.getGhiChu());
                row.createCell(5).setCellValue(nhanVien.getChucVu().getMaChucVu());
                row.createCell(6).setCellValue(nhanVien.getNgaySinh().toString());
                row.createCell(7).setCellValue(nhanVien.getNguoiNhapThongTin());
                row.createCell(8).setCellValue(nhanVien.getSoCmnd());
                row.createCell(9).setCellValue(nhanVien.getSoDienThoai());
                row.createCell(10).setCellValue(nhanVien.getUrlHinhDaiDien());
                row.createCell(11).setCellValue(nhanVien.getGioiTinh());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }
}
