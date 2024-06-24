package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buoihoc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuoiHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maBuoiHoc;

    @Column(name = "ten_buoi_hoc", length = 255)
    private String tenBuoiHoc;

    @Column(name = "noi_dung_buoi_hoc", length = 255)
    private String noiDungBuoiHoc;

    @ManyToOne
    @JoinColumn(name = "ma_module")
    private Module module;
}
