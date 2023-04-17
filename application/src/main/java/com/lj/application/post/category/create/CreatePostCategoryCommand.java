package com.lj.application.post.category.create;

public record CreatePostCategoryCommand(
        String name,
        String user
) {

    public static CreatePostCategoryCommand with(final String aName,
                                                 final String anUser) {
        return new CreatePostCategoryCommand(aName,
                                        anUser);
    }

}
