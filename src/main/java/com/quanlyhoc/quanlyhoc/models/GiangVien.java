package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "giangvien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiangVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maGiangVien;

    @Column(name = "co_quan_cong_tac", length = 255)
    private String coQuanCongTac;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_linh_vuc")
    private LinhVuc linhVuc;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "so_cmnd", length = 255)
    private String soCmnd;

    @Column(name = "so_dien_thoai", length = 255)
    private String soDienThoai;

    @Column(name = "ten_giang_vien", length = 255)
    private String tenGiangVien;

    @Column(name = "tinh_trang_cong_tac", length = 255)
    private String tinhTrangCongTac;

    @Column(name = "url_hinh_dai_dien", length = 255)
    private String urlHinhDaiDien;

    @Column(name = "gioi_tinh", length = 255)
    private String gioiTinh;

    @ManyToOne
    @JoinColumn(name = "ma_tai_khoan")
    private TaiKhoan taiKhoan;
}
