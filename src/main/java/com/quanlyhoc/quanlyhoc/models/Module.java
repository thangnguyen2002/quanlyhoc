package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "module")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maModule;

    @Column(name = "ten_module", length = 255)
    private String tenModule;

    @Column(name = "mo_ta", length = 255)
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "ma_khoa_hoc")
    private KhoaHoc khoaHoc;
}
