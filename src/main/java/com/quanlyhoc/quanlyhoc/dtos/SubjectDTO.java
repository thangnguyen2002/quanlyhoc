package com.quanlyhoc.quanlyhoc.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDTO {
    @JsonProperty("subject_name")
    private String subjectName;

    @JsonProperty("teacher_id")
    private Long teacherId;

    @JsonProperty("note")
    private String note;
}