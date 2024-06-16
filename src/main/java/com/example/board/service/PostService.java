package com.example.board.service;

import com.example.board.dto.PostPatchRequestBody;
import com.example.board.dto.PostPostRequestBody;
import com.example.board.dto.Post;
import com.example.board.exception.post.PostNotFoundException;
import com.example.board.model.entity.PostEntity;
import com.example.board.repository.PostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostEntityRepository postEntityRepository;

    public List<Post> getPosts() {
        return postEntityRepository.findAll();
    }

    public Optional<Post> getPostByPostId(Long postId) {
        var postEntity = postEntityRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        return Post.from(postEntity);
    }

    public Post createPost(PostPostRequestBody postPostRequestBody) {
        Long newPostId = posts.stream()
                .mapToLong(Post::getPostId)
                .max()
                .orElse(0L) + 1;

        Post newPost = new Post(newPostId, postPostRequestBody.body(), ZonedDateTime.now());
        posts.add(newPost);
        return newPost;
    }

    public Post updatePost(Long postId, PostPatchRequestBody postPatchRequestBody) {
        PostEntity postEntity = postEntityRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        postEntity.setBody(postPatchRequestBody.body());
        var updatedPostEntity = postEntityRepository.save(postEntity);
        return Post.from(updatedPostEntity);
    }

    public void deletePost(Long postId) {
        var postEntity = postEntityRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        postEntityRepository.delete(postEntity);
    }
}
