package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "hocvien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HocVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHocVien;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "so_cmnd", length = 255)
    private String soCmnd;

    @Column(name = "so_dien_thoai", length = 255)
    private String soDienThoai;

    @Column(name = "ten_hoc_vien", length = 255)
    private String tenHocVien;

    @Column(name = "url_hinh_dai_dien", length = 255)
    private String urlHinhDaiDien;

    @Column(name = "gioi_tinh", length = 255)
    private String gioiTinh;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "tinh_trang_hoc_tap", length = 255)
    private String tinhTrangHocTap;

    @Column(name = "ngay_cap_nhat_gan_nhat")
    private LocalDate ngayCapNhatGanNhat;

    @ManyToOne
    @JoinColumn(name = "ma_tai_khoan")
    private TaiKhoan taiKhoan;
}
