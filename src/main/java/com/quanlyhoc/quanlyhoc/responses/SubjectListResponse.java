package com.quanlyhoc.quanlyhoc.responses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SubjectListResponse {
    private List<SubjectResponse> subjects;
    private int totalPages;
}
