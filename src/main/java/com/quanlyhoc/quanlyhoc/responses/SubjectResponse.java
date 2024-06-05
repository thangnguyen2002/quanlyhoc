//package com.quanlyhoc.quanlyhoc.responses;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.quanlyhoc.quanlyhoc.models.Subject;
//import lombok.*;
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//public class SubjectResponse {
//    @JsonProperty("subject_name")
//    private String subjectName;
//
//    @JsonProperty("teacher_name")
//    private String teacherName;
//
//    @JsonProperty("note")
//    private String note;
//
//    public static SubjectResponse fromSubject(Subject subject) {
//
//        return SubjectResponse.builder()
//                .subjectName(subject.getSubjectName())
//                .teacherName(subject.getUser().getFullName())
//                .build();
//    }
//}
