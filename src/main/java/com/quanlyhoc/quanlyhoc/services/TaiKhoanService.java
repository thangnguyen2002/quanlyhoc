package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.dtos.TaiKhoanDTO;
import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.repositories.NhanVienRepository;
import com.quanlyhoc.quanlyhoc.repositories.TaiKhoanRepository;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITaiKhoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaiKhoanService implements ITaiKhoanService {
    @Autowired
    private final TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private final NhanVienRepository nhanVienRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public TaiKhoan themTaiKhoan(TaiKhoanDTO taiKhoanDTO) throws Exception {
        Long maNhanvien = taiKhoanDTO.getMaNhanVien();
        NhanVien exNhanVien = nhanVienRepository.findById(maNhanvien)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + maNhanvien));

        TaiKhoan taiKhoan = TaiKhoan.builder()
                .ghiChu(taiKhoanDTO.getGhiChu())
                .nhanVien(exNhanVien)
                .matKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()))
                .quyenTruyCap(taiKhoanDTO.getQuyenTruyCap())
                .tenTaiKhoan(taiKhoanDTO.getTenTaiKhoan())
                .build();

        return taiKhoanRepository.save(taiKhoan);
    }

    @Transactional
    @Override
    public TaiKhoan suaTaiKhoan(Long id, TaiKhoanDTO taiKhoanDTO) throws Exception {
        TaiKhoan exTaiKhoan = taiKhoanRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy tài khoản với mã: " + id));

        Long maNhanvien = taiKhoanDTO.getMaNhanVien();
        NhanVien exNhanVien = nhanVienRepository.findById(maNhanvien)
                .orElseThrow(() -> new DataNotFoundException("Không tìm thấy nhân viên với mã: " + maNhanvien));

        exTaiKhoan.setGhiChu(taiKhoanDTO.getGhiChu());
        exTaiKhoan.setNhanVien(exNhanVien);
        exTaiKhoan.setMatKhau(passwordEncoder.encode(taiKhoanDTO.getMatKhau()));
        exTaiKhoan.setQuyenTruyCap(taiKhoanDTO.getQuyenTruyCap());
        exTaiKhoan.setTenTaiKhoan(taiKhoanDTO.getTenTaiKhoan());

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
}
