package com.lj.infrastructure.post.subcategory.persistence;

import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;
import com.lj.domain.post.subcategory.PostSubcategory;
import com.lj.domain.post.subcategory.PostSubcategoryID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_subcategory")
public class PostSubcategoryJpaEntity {
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "post_category", nullable = false)
    private String postCategory;

    public PostSubcategoryJpaEntity() {}

    public PostSubcategoryJpaEntity(final String id,
                                    final String name,
                                    final String postCategory) {
        this.id = id;
        this.name = name;
        this.postCategory = postCategory;
    }

    public static PostSubcategoryJpaEntity from(final PostSubcategory aPostSubcategory) {
        return new PostSubcategoryJpaEntity(
                aPostSubcategory.getId().getValue(),
                aPostSubcategory.getName(),
                aPostSubcategory.getPostCategory()
        );
    }

    public PostSubcategory toAggregate() {
        return PostSubcategory.with(
                PostSubcategoryID.from(id),
                name,
                postCategory
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }
}
