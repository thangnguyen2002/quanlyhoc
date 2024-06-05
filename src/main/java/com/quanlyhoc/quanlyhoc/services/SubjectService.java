//package com.quanlyhoc.quanlyhoc.services;
//
//import com.quanlyhoc.quanlyhoc.dtos.SubjectDTO;
//import com.quanlyhoc.quanlyhoc.exceptions.DataNotFoundException;
//import com.quanlyhoc.quanlyhoc.models.User;
//import com.quanlyhoc.quanlyhoc.repositories.SubjectRepository;
//import com.quanlyhoc.quanlyhoc.repositories.UserRepository;
//import com.quanlyhoc.quanlyhoc.models.Subject;
//import com.quanlyhoc.quanlyhoc.responses.SubjectResponse;
//import com.quanlyhoc.quanlyhoc.services.interfaces.ISubjectService;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class SubjectService implements ISubjectService {
//    @Autowired
//    private final UserRepository userRepository;
//
//    @Autowired
//    private final SubjectRepository subjectRepository;
//
//    @Transactional
//    @Override
//    public SubjectResponse createSubject(SubjectDTO subjectDTO) throws Exception {
//        User exUser = userRepository.findById(subjectDTO.getTeacherId())
//                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + subjectDTO.getTeacherId()));
//
//        Subject subject = Subject.builder()
//                .subjectName(subjectDTO.getSubjectName())
//                .user(exUser)
//                .note(subjectDTO.getNote())
//                .build();
//
//        subjectRepository.save(subject);
//
//        return SubjectResponse.builder()
//                .subjectName(subjectDTO.getSubjectName())
//                .teacherName(exUser.getFullName())
//                .note(subjectDTO.getNote())
//                .build();
//    }
//
//    @Transactional
//    @Override
//    public Subject getSubject(Long id) throws Exception {
//        return subjectRepository.findById(id)
//                .orElseThrow(() -> new DataNotFoundException("Cannot find with subject id: " + id));
//    }
//
//    @Transactional
//    @Override
//    public Subject updateSubject(Long id, SubjectDTO subjectDTO) throws Exception {
//        Subject exSubject = subjectRepository.findById(id)
//                .orElseThrow(() -> new DataNotFoundException("Cannot find with subject id: " + id));
//
//        User exUser = userRepository.findById(subjectDTO.getTeacherId())
//                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + subjectDTO.getTeacherId()));
//
//        exSubject.setSubjectName(subjectDTO.getSubjectName());
//        exSubject.setUser(exUser);
//        exSubject.setNote(subjectDTO.getNote());
//
//        return subjectRepository.save(exSubject);
//    }
//
//    @Transactional
//    @Override
//    public void deleteSubject(Long id) throws Exception {
//        Optional<Subject> exSubject = subjectRepository.findById(id);
//        exSubject.ifPresent(subjectRepository::delete);
//    }
//
//    @Override
//    public Page<SubjectResponse> getAllSubjects(Long teacherId, String keyword, PageRequest pageRequest) {
//        Page<Subject> subjectPage;
//        subjectPage = subjectRepository.searchSubjects(teacherId, keyword, pageRequest);
//        return subjectPage.map(SubjectResponse::fromSubject);
//    }
//}
