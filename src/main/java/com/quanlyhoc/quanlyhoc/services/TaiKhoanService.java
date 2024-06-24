package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.GiangVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.HocVienDTO;
import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import com.quanlyhoc.quanlyhoc.models.LinhVuc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.repositories.GiangVienRepository;
import com.quanlyhoc.quanlyhoc.repositories.HocVienRepository;
import com.quanlyhoc.quanlyhoc.repositories.LinhVucRepository;
import com.quanlyhoc.quanlyhoc.repositories.TaiKhoanRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITaiKhoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaiKhoanService implements ITaiKhoanService {
    @Autowired
    private final TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private final GiangVienRepository giangVienRepository;

    @Autowired
    private final HocVienRepository hocVienRepository;

    @Autowired
    private final LinhVucRepository linhVucRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final FileService fileService;

    @Transactional
    @Override
    public TaiKhoan themTaiKhoan(TaiKhoanDTO taiKhoanDTO, MultipartFile file) throws Exception {
        // Kiểm tra nếu tên tài khoản đã tồn tại
        if (taiKhoanRepository.existsByTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan())) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()));
        taiKhoan.setVaiTro(taiKhoanDTO.getVaiTro());
        taiKhoan.setGhiChu(taiKhoanDTO.getGhiChu());

        taiKhoan = taiKhoanRepository.save(taiKhoan);

        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            fileUrl = fileService.saveFile(file);
        }

        switch (taiKhoanDTO.getVaiTro().toUpperCase()) {
            case "HOCVIEN":
                HocVienDTO hocVienDTO = (HocVienDTO) taiKhoanDTO;
                HocVien hocVien = new HocVien();
                hocVien.setTaiKhoan(taiKhoan);
                hocVien.setDiaChi(hocVienDTO.getDiaChi());
                hocVien.setEmail(hocVienDTO.getEmail());
                hocVien.setGhiChu(hocVienDTO.getGhiChu());
                hocVien.setSoCmnd(hocVienDTO.getSoCmnd());
                hocVien.setSoDienThoai(hocVienDTO.getSoDienThoai());
                hocVien.setTenHocVien(hocVienDTO.getTenHocVien());
                hocVien.setUrlHinhDaiDien(fileUrl);
                hocVien.setGioiTinh(hocVienDTO.getGioiTinh());
                hocVien.setNgaySinh(hocVienDTO.getNgaySinh());
                hocVien.setTinhTrangHocTap(hocVienDTO.getTinhTrangHocTap());
                hocVien.setNgayCapNhatGanNhat(LocalDate.now());

                hocVienRepository.save(hocVien);
                break;

            case "GIANGVIEN":
                 GiangVienDTO giangVienDTO = (GiangVienDTO) taiKhoanDTO;
                LinhVuc linhVuc = linhVucRepository.findById(giangVienDTO.getMaLinhVuc())
                        .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + giangVienDTO.getMaLinhVuc()));

                GiangVien giangVien = new GiangVien();
                giangVien.setTaiKhoan(taiKhoan);
                giangVien.setCoQuanCongTac(giangVienDTO.getCoQuanCongTac());
                giangVien.setDiaChi(giangVienDTO.getDiaChi());
                giangVien.setEmail(giangVienDTO.getEmail());
                giangVien.setGhiChu(giangVienDTO.getGhiChu());
                giangVien.setLinhVuc(linhVuc);
                giangVien.setNgaySinh(giangVienDTO.getNgaySinh());
                giangVien.setSoCmnd(giangVienDTO.getSoCmnd());
                giangVien.setSoDienThoai(giangVienDTO.getSoDienThoai());
                giangVien.setTenGiangVien(giangVienDTO.getTenGiangVien());
                giangVien.setTinhTrangCongTac(giangVienDTO.getTinhTrangCongTac());
                giangVien.setUrlHinhDaiDien(fileUrl);
                giangVien.setGioiTinh(giangVienDTO.getGioiTinh());

                giangVienRepository.save(giangVien);
                break;

            default:
                // Nếu không phải học viên hoặc giảng viên, không làm gì thêm
                break;
        }

        return taiKhoan;
    }



    @Transactional
    @Override
    public TaiKhoan suaTaiKhoan(Long id, TaiKhoanDTO taiKhoanDTO) throws Exception {
        TaiKhoan exTaiKhoan = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + id));

        exTaiKhoan.setGhiChu(taiKhoanDTO.getGhiChu());
        exTaiKhoan.setTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan());
        exTaiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()));
        exTaiKhoan.setVaiTro(taiKhoanDTO.getVaiTro());

        return taiKhoanRepository.save(exTaiKhoan);
    }

    @Transactional
    @Override
    public void xoaTaiKhoan(Long id) throws Exception {
        TaiKhoan exTaiKhoan = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + id));
        taiKhoanRepository.delete(exTaiKhoan);
    }

    @Transactional
    @Override
    public List<TaiKhoan> timTaiKhoanTheoTen(String tenTaiKhoan) throws Exception {
        return taiKhoanRepository.findByTenTaiKhoanContaining(tenTaiKhoan);
    }

    @Transactional
    @Override
    public Page<TaiKhoan> findAll(Pageable pageable) throws Exception {
        return taiKhoanRepository.findAll(pageable);
    }

    @Override
    public TaiKhoan findById(Long id) throws Exception {
        return taiKhoanRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + id));
    }
}
