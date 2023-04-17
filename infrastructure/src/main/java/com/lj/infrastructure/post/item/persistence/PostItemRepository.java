package com.lj.infrastructure.post.item.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostItemRepository extends JpaRepository<PostItemJpaEntity, String> {

    List<PostItemJpaEntity> findAllByPostOrderByNumberAsc(String user);
}
