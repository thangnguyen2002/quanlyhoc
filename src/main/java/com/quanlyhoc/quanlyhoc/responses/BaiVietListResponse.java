package com.quanlyhoc.quanlyhoc.responses;

import com.quanlyhoc.quanlyhoc.models.BaiViet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BaiVietListResponse {
    private List<BaiViet> baiVietList;
    private int totalPages;
}
