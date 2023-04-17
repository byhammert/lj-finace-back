package com.lj.infrastructure.post.category.persistence;

import com.lj.domain.account.Account;
import com.lj.domain.account.AccountID;
import com.lj.domain.post.category.PostCategory;
import com.lj.domain.post.category.PostCategoryID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "post_category")
public class PostCategoryJpaEntity {
    @Id
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "user_id", nullable = false)
    private String user;

    public PostCategoryJpaEntity() {}

    public PostCategoryJpaEntity(final String id,
                                 final String name,
                                 final String user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    public static PostCategoryJpaEntity from(final PostCategory aPostCategory) {
        return new PostCategoryJpaEntity(
                aPostCategory.getId().getValue(),
                aPostCategory.getName(),
                aPostCategory.getUser()

        );
    }

    public PostCategory toAggregate() {
        return PostCategory.with(
                PostCategoryID.from(id),
                name,
                user
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
