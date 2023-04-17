package com.lj.domain.post.category;

import com.lj.domain.AggregateRoot;
import com.lj.domain.post.item.PostItemID;
import com.lj.domain.post.item.PostItemValidator;
import com.lj.domain.validation.ValidationHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostCategory extends AggregateRoot<PostCategoryID> implements Cloneable {

    private String name;
    private String user;

    public PostCategory(final PostCategoryID aPostItemID,
                        final String aName,
                        final String anUser) {
        super(aPostItemID);
        this.name = aName;
        this.user = anUser;
    }

    public static PostCategory newPostCategory(final String aName,
                                               final String anUser) {

        final var id = PostCategoryID.unique();

        return new PostCategory(id,
                                aName,
                                anUser);
    }

    public static PostCategory with(final PostCategoryID anId,
                                    final String aName,
                                    final String anUser) {
        return new PostCategory(anId,
                                aName,
                                anUser);
    }

    public PostCategory update(final String aName) {
        this.name = aName;

        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new PostCategoryValidator(this, aHandler).validate();
    }

    @Override
    public PostCategory clone() {
        try {
            return (PostCategory) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
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
