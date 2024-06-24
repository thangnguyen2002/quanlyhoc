package com.quanlyhoc.quanlyhoc.responses;

import com.quanlyhoc.quanlyhoc.models.GiangVien;
import com.quanlyhoc.quanlyhoc.models.HocVien;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class HocVienListResponse {
    private List<HocVien> hocVienList;
    private int totalPages;
}
