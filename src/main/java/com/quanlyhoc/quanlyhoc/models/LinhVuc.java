package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "linhvuc")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinhVuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLinhVuc;

    @Column(name = "tenLinhVuc", length = 250)
    private String tenLinhVuc;
}
