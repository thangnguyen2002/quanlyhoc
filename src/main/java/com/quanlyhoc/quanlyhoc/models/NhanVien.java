package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "nhanvien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhanVien;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_chuc_vu")
    private ChucVu chucVu;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "nguoi_nhap_thong_tin", length = 255)
    private String nguoiNhapThongTin;

    @Column(name = "so_cmnd", length = 255)
    private String soCmnd;

    @Column(name = "so_dien_thoai", length = 255)
    private String soDienThoai;

    @Column(name = "ten_nhan_vien", length = 255)
    private String tenNhanVien;

    @Column(name = "url_hinh_dai_dien", length = 255)
    private String urlHinhDaiDien;

    @Column(name = "gioi_tinh", length = 255)
    private String gioiTinh;
}
