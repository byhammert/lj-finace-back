package com.lj.infrastructure.post.category.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCategoryRepository extends JpaRepository<PostCategoryJpaEntity, String> {

    List<PostCategoryJpaEntity> findAllByUserOrderByNameAsc(String user);
}
