package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "lienhe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LienHe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhach;

    @Column(name = "email", length = 250)
    private String email;

    @Column(name = "hoTen", length = 250)
    private String hoTen;

    @Column(name = "ngayLienHe", length = 250)
    private LocalDate ngayLienHe;

    @Column(name = "soDienThoai", length = 250)
    private String soDienThoai;

    @Column(name = "yKien", length = 250)
    private String yKien;
}
