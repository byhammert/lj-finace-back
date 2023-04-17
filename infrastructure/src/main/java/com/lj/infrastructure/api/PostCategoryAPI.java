package com.lj.infrastructure.api;

import com.lj.infrastructure.post.category.models.CreatePostCategoryApiInput;
import com.lj.infrastructure.post.category.models.PostCategoriesListApiOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/posts/categories")
@Tag(name = "Posts Categories")
public interface PostCategoryAPI {

    @PostMapping()
    @Operation(summary = "Create a new post category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post category created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createPostCategory(Principal principal, @RequestBody CreatePostCategoryApiInput input);

    @GetMapping()
    @Operation(summary = "List all post categories of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    List<PostCategoriesListApiOutput> listPostCategories(Principal principal);

}
