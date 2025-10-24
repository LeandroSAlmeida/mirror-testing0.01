package com.mirror.project.core.domain;

import java.util.List;

public record Page<T>(
        List<T> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages

) {

    public Page {
        if (content == null) {
            throw new IllegalArgumentException("content cannot be null");
        }
        if (pageNumber < 0) {
            throw new IllegalArgumentException("pageNumber cannot be negative");
        }
        if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize cannot be less than 1");
        }
    }

    public boolean hasNext() {
        return pageNumber < totalPages -1;
    }

    public boolean hasPrevious(){
        return pageNumber > 0;
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }
}
