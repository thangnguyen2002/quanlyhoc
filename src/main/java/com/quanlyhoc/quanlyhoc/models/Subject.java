package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name", length = 100)
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User user;

    @Column(name = "note", length = 250)
    private String note;
}
