package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chucvu")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChucVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChucVu;

    @Column(name = "ten_chuc_vu", length = 255)
    private String tenChucVu;

    @Column(name = "trang_thai", length = 255)
    private String trangThai;
}
