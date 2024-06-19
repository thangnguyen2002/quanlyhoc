package com.quanlyhoc.quanlyhoc.responses;

import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.KhoaHoc;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class KhoaHocListResponse {
    private List<KhoaHoc> khoaHocList;
    private int totalPages;
}
