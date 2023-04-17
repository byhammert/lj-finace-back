package com.lj.infrastructure.api;

import com.lj.infrastructure.post.subcategory.PostSubcategoriesListApiPresenter;
import com.lj.infrastructure.post.subcategory.models.CreatePostSubcategoryApiInput;
import com.lj.infrastructure.post.subcategory.models.PostSubcategoriesListApiOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/posts/subcategories")
@Tag(name = "Posts Subcategories")
public interface PostSubcategoryAPI {

    @PostMapping()
    @Operation(summary = "Create a new post subcategory")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post subcategory created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createPostSubcategory(Principal principal, @RequestBody CreatePostSubcategoryApiInput input);

    @GetMapping(value = "{category}")
    @Operation(summary = "List all post subcategories of post category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    List<PostSubcategoriesListApiOutput> listPostCategories(@PathVariable(name = "category") String category);

}
