package com.lj.domain;

public record PaginationSearchQuery(
        int page,
        int perPage,
        String terms,
        String sort,
        String direction
) {
}
