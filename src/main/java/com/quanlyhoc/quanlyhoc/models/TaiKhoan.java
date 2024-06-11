package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTaiKhoan;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @Column(name = "mat_khau", length = 255)
    private String matKhau;

    @Column(name = "quyen_truy_cap", length = 255)
    private String quyenTruyCap;

    @Column(name = "ten_tai_khoan", length = 255)
    private String tenTaiKhoan;
}
