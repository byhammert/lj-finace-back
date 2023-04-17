package com.lj.application.post.item.create;

import com.lj.domain.post.item.PostItem;

import java.util.List;

public record CreateListPostItemOutput(
        List<String> ids
) {
    public static CreateListPostItemOutput fromStrings(final List<String> anIds) {
        return new CreateListPostItemOutput(anIds);
    }

    public static CreateListPostItemOutput from(final List<PostItem> aPostItems) {
        return new CreateListPostItemOutput(aPostItems.stream().map(elem -> elem.getId().getValue()).toList());
    }
}
