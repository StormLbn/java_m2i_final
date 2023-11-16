package com.example.filrouge_back.models.entitydtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {

    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private List<T> content;
}
