//package com.quanlyhoc.quanlyhoc.services.interfaces;
//
//import com.quanlyhoc.quanlyhoc.dtos.SubjectDTO;
//import com.quanlyhoc.quanlyhoc.models.Subject;
//import com.quanlyhoc.quanlyhoc.responses.SubjectResponse;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//
//public interface ISubjectService {
//    SubjectResponse createSubject(SubjectDTO subjectDTO) throws Exception;
//    Subject getSubject(Long id) throws Exception;
//    Subject updateSubject(Long id, SubjectDTO subjectDTO) throws Exception;
//    void deleteSubject(Long id) throws Exception;
//    Page<SubjectResponse> getAllSubjects(Long teacherId, String keyword, PageRequest pageRequest);
//
//}
