package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "khoahoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KhoaHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhoaHoc;

    @Column(name = "hocPhi")
    private Double hocPhi;

    @ManyToOne
    @JoinColumn(name = "maLinhVuc")
    private LinhVuc linhVuc;

    @Column(name = "soBuoi")
    private Integer soBuoi;

    @Column(name = "tenKhoaHoc", length = 250)
    private String tenKhoaHoc;

    @Column(name = "ghiChu", length = 250)
    private String ghiChu;

    @Column(name = "noiDungKhoaHoc", length = 250)
    private String noiDungKhoaHoc;

    @Column(name = "noiDungTomTatKhoaHoc", length = 250)
    private String noiDungTomTatKhoaHoc;
}
