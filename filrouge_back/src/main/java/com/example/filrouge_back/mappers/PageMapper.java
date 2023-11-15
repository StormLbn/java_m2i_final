package com.example.filrouge_back.mappers;

import com.example.filrouge_back.models.entitydtos.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PageMapper {

    public <T, U> PageDTO<U> pageToPageDto(Page<T> page, List<U> mappedContent) {
        if (page == null || mappedContent == null) {
            return null;
        } else {
            return PageDTO.<U>builder()
                .pageNumber(page.getPageable().getPageNumber())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .content(mappedContent)
                .build();
        }
    }
}
