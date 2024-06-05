package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "phonghoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhongHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhongHoc;

    @Column(name = "ghiChu", length = 100)
    private String ghiChu;

    @Column(name = "soChoNgoi")
    private Integer soChoNgoi;

    @Column(name = "TenPhongHoc", length = 250)
    private String TenPhongHoc;
}
