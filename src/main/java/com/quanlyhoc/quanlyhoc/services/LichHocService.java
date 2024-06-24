//package com.quanlyhoc.quanlyhoc.services;
//
//public class LichHocService {
//    //ADMIN
//    public void createLichHoc(LichHoc lichHoc) {
//        // Bỏ trống trường file_diem_danh khi tạo mới lịch học
//        lichHoc.setFileDiemDanh(null);
//        // Logic để tạo mới lịch học
//        lichHocRepository.save(lichHoc);
//    }
//
//    public void updateLichHoc(LichHoc lichHoc) {
//        // Bỏ trống trường file_diem_danh khi cập nhật lịch học
//        lichHoc.setFileDiemDanh(null);
//        // Logic để cập nhật lịch học
//        lichHocRepository.save(lichHoc);
//    }
//
//    public List<LichHoc> viewAllLichHoc() {
//        // Logic để quản trị viên xem tất cả lịch học bao gồm file điểm danh nếu có
//        return lichHocRepository.findAll();
//    }
//
//
//    //Giang vien
//    public void uploadDiemDanh(int maLichHoc, String filePath) {
//        // Tìm lịch học theo mã
//        LichHoc lichHoc = lichHocRepository.findById(maLichHoc).orElseThrow(() -> new ResourceNotFoundException("Lịch học không tồn tại"));
//        // Cập nhật trường file_diem_danh
//        lichHoc.setFileDiemDanh(filePath);
//        lichHocRepository.save(lichHoc);
//    }
//
//    public List<LichHoc> viewLichHocForTeacher() {
//        // Logic để giảng viên xem lịch học bao gồm cả file điểm danh
//        return lichHocRepository.findAll();
//    }
//
//
//    //Xem lich hoc
//    public List<LichHoc> viewLichHocForStudent() {
//        // Logic để học viên xem lịch học không bao gồm trường file_diem_danh
//        return lichHocRepository.findAll().stream()
//                .map(lichHoc -> {
//                    lichHoc.setFileDiemDanh(null); // Ẩn thông tin file điểm danh
//                    return lichHoc;
//                })
//                .collect(Collectors.toList());
//    }
//
//}
