package com.lj.application.post.subcategory.create;

public record CreatePostSubcategoryCommand(
        String name,
        String postCategory
) {

    public static CreatePostSubcategoryCommand with(final String aName,
                                                    final String postCategory) {
        return new CreatePostSubcategoryCommand(aName,
                postCategory);
    }

}
