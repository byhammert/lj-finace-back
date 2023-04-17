package com.lj.infrastructure.post.subcategory.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostSubcategoryRepository extends JpaRepository<PostSubcategoryJpaEntity, String> {

    List<PostSubcategoryJpaEntity> findAllByPostCategoryOrderByNameAsc(String user);
}
