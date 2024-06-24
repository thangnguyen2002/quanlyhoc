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

    @Column(name = "ten_tai_khoan", length = 255)
    private String tenTaiKhoan;

    @Column(name = "mat_khau", length = 255)
    private String matKhau;

    @Column(name = "vai_tro", length = 255)
    private String vaiTro;
}
