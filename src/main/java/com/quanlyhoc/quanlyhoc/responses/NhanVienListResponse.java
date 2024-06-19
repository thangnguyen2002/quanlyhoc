package com.quanlyhoc.quanlyhoc.responses;

import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.NhanVien;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class NhanVienListResponse {
    private List<NhanVien> nhanVienList;
    private int totalPages;
}
