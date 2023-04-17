package com.lj.infrastructure.api;

import com.lj.domain.pagination.Pagination;
import com.lj.infrastructure.post.models.PostsListApiOutput;
import com.lj.infrastructure.post.models.expense.CreateExpensePostApiInput;
import com.lj.infrastructure.post.models.revenue.CreateRevenuePostApiInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping(value = "/posts")
@Tag(name = "Posts")
public interface PostAPI {

    @PostMapping("/revenues")
    @Operation(summary = "Create a new post of type revenue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createPostRevenue(Principal principal, @RequestBody CreateRevenuePostApiInput input);

    @PostMapping("/expenses")
    @Operation(summary = "Create a new post of type expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    ResponseEntity<?> createPostExpense(Principal principal, @RequestBody CreateExpensePostApiInput input);

    @GetMapping()
    @Operation(summary = "List all pasts paginated of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown")
    })
    Pagination<PostsListApiOutput> listPosts(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "description") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String dir
    );

}
