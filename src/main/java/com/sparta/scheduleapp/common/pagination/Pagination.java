package com.sparta.scheduleapp.common.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter

public class Pagination {
    private int currentPage;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public Pagination(Page<?> page) {
        this.currentPage = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
    }
}
