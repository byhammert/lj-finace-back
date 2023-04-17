package com.lj.domain.post.subcategory;

import com.lj.domain.AggregateRoot;
import com.lj.domain.validation.ValidationHandler;

public class PostSubcategory extends AggregateRoot<PostSubcategoryID> implements Cloneable {

    private String name;
    private String postCategory;

    public PostSubcategory(final PostSubcategoryID aPostItemID,
                           final String aName,
                           final String aCategory) {
        super(aPostItemID);
        this.name = aName;
        this.postCategory = aCategory;
    }

    public static PostSubcategory newPostSubcategory(final String aName,
                                                     final String aCategory) {

        final var id = PostSubcategoryID.unique();

        return new PostSubcategory(id,
                                aName,
                                aCategory);
    }

    public static PostSubcategory with(final PostSubcategoryID anId,
                                       final String aName,
                                       final String aCategory) {
        return new PostSubcategory(anId,
                                aName,
                                aCategory);
    }

    public PostSubcategory update(final String aName,
                                  final String aCategory) {
        this.name = aName;
        this.postCategory = aCategory;

        return this;
    }

    @Override
    public void validate(ValidationHandler aHandler) {
        new PostSubcategoryValidator(this, aHandler).validate();
    }

    @Override
    public PostSubcategory clone() {
        try {
            return (PostSubcategory) super.clone();
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

    public String getPostCategory() {
        return postCategory;
    }

    public void setPostCategory(String postCategory) {
        this.postCategory = postCategory;
    }
}
