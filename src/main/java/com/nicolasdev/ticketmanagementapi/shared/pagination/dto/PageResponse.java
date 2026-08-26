package com.nicolasdev.ticketmanagementapi.shared.pagination.dto;

import java.util.List;


public record PageResponse<T> (
        List<T> content,
        int currentPage,
        int pageSize,
        int totalPages,
        long totalElements){}