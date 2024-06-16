package com.example.board.controller;

import com.example.board.dto.PostPatchRequestBody;
import com.example.board.dto.PostPostRequestBody;
import com.example.board.dto.Post;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        logger.info("GET /api/v1/posts");
        List<Post> posts = postService.getPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostByPostId(@PathVariable Long postId) {
        logger.info("GET /api/v1/posts/{}", postId);
        Optional<Post> matchingPost = postService.getPostByPostId(postId);
        return matchingPost
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostPostRequestBody postPostRequestBody) {
        logger.info("POST /api/v1/posts");
        Post post = postService.createPost(postPostRequestBody);
        return ResponseEntity.ok(post);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId,
                                           @RequestBody PostPatchRequestBody postPatchRequestBody) {
        logger.info("PATCH /api/v1/posts/{}", postId);
        Post post = postService.updatePost(postId, postPatchRequestBody);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        logger.info("DELETE /api/v1/posts/{}", postId);
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
