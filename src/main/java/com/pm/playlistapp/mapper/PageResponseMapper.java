package com.pm.playlistapp.mapper;

import com.pm.playlistapp.dto.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class PageResponseMapper {

    public <T> PageResponse<T> toPageResponse(Page<?> page, List<T> content) {

        return PageResponse.<T>builder()
                .content(content)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
}