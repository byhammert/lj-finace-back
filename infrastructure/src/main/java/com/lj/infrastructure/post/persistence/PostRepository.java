package com.lj.infrastructure.post.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostJpaEntity, String> {

    Page<PostJpaEntity> findAll(Specification<PostJpaEntity> whereClause, Pageable page);
}
