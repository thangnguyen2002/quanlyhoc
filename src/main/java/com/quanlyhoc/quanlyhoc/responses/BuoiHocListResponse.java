package com.quanlyhoc.quanlyhoc.responses;

import com.quanlyhoc.quanlyhoc.models.BuoiHoc;
import com.quanlyhoc.quanlyhoc.models.LienHe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class BuoiHocListResponse {
    private List<BuoiHoc> buoiHocList;
    private int totalPages;
}
