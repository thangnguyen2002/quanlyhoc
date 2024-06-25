package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.*;
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
    public TaiKhoan themTaiKhoanAdmin(TaiKhoanDTO taiKhoanDTO) throws Exception {
        // Kiểm tra nếu tên tài khoản đã tồn tại
        if (taiKhoanRepository.existsByTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan())) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()));
        taiKhoan.setVaiTro(taiKhoanDTO.getVaiTro());
        taiKhoan.setGhiChu(taiKhoanDTO.getGhiChu());

        return taiKhoanRepository.save(taiKhoan);
    }


    @Override
    public TaiKhoan themTaiKhoanGiangVien(TaiKhoan_GiangVienDTO taiKhoanGiangVienDTO, MultipartFile file) throws Exception {
        if (taiKhoanRepository.existsByTenTaiKhoan(taiKhoanGiangVienDTO.getTenTaiKhoan())) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(taiKhoanGiangVienDTO.getTenTaiKhoan());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanGiangVienDTO.getMatKhau()));
        taiKhoan.setVaiTro(taiKhoanGiangVienDTO.getVaiTro());
        taiKhoan.setGhiChu(taiKhoanGiangVienDTO.getGhiChu());

        taiKhoan = taiKhoanRepository.save(taiKhoan);

        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            fileUrl = fileService.saveFile(file);
        }

        LinhVuc linhVuc = linhVucRepository.findById(taiKhoanGiangVienDTO.getMaLinhVuc())
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy lĩnh vực với mã: " + taiKhoanGiangVienDTO.getMaLinhVuc()));

        GiangVien giangVien = new GiangVien();
        giangVien.setTaiKhoan(taiKhoan);
        giangVien.setCoQuanCongTac(taiKhoanGiangVienDTO.getCoQuanCongTac());
        giangVien.setDiaChi(taiKhoanGiangVienDTO.getDiaChi());
        giangVien.setEmail(taiKhoanGiangVienDTO.getEmail());
        giangVien.setGhiChu(taiKhoanGiangVienDTO.getGhiChu());
        giangVien.setLinhVuc(linhVuc);
        giangVien.setNgaySinh(taiKhoanGiangVienDTO.getNgaySinh());
        giangVien.setSoCmnd(taiKhoanGiangVienDTO.getSoCmnd());
        giangVien.setSoDienThoai(taiKhoanGiangVienDTO.getSoDienThoai());
        giangVien.setTenGiangVien(taiKhoanGiangVienDTO.getTenGiangVien());
        giangVien.setTinhTrangCongTac(taiKhoanGiangVienDTO.getTinhTrangCongTac());
        giangVien.setUrlHinhDaiDien(fileUrl);
        giangVien.setGioiTinh(taiKhoanGiangVienDTO.getGioiTinh());

        giangVienRepository.save(giangVien);

        return taiKhoan;
    }

    @Override
    public TaiKhoan themTaiKhoanHocVien(TaiKhoan_HocVienDTO taiKhoanHocVienDTO, MultipartFile file) throws Exception {
        if (taiKhoanRepository.existsByTenTaiKhoan(taiKhoanHocVienDTO.getTenTaiKhoan())) {
            throw new Exception("Tên tài khoản đã tồn tại");
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setTenTaiKhoan(taiKhoanHocVienDTO.getTenTaiKhoan());
        taiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanHocVienDTO.getMatKhau()));
        taiKhoan.setVaiTro(taiKhoanHocVienDTO.getVaiTro());
        taiKhoan.setGhiChu(taiKhoanHocVienDTO.getGhiChu());

        taiKhoan = taiKhoanRepository.save(taiKhoan);

        String fileUrl = null;
        if (file != null && !file.isEmpty()) {
            fileUrl = fileService.saveFile(file);
        }

        HocVien hocVien = new HocVien();
        hocVien.setTaiKhoan(taiKhoan);
        hocVien.setDiaChi(taiKhoanHocVienDTO.getDiaChi());
        hocVien.setEmail(taiKhoanHocVienDTO.getEmail());
        hocVien.setGhiChu(taiKhoanHocVienDTO.getGhiChu());
        hocVien.setSoCmnd(taiKhoanHocVienDTO.getSoCmnd());
        hocVien.setSoDienThoai(taiKhoanHocVienDTO.getSoDienThoai());
        hocVien.setTenHocVien(taiKhoanHocVienDTO.getTenHocVien());
        hocVien.setUrlHinhDaiDien(fileUrl);
        hocVien.setGioiTinh(taiKhoanHocVienDTO.getGioiTinh());
        hocVien.setNgaySinh(taiKhoanHocVienDTO.getNgaySinh());
        hocVien.setTinhTrangHocTap(taiKhoanHocVienDTO.getTinhTrangHocTap());
        hocVien.setNgayCapNhatGanNhat(LocalDate.now());

        hocVienRepository.save(hocVien);

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
