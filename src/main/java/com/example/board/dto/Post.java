package com.example.board.dto;

import com.example.board.model.entity.PostEntity;

import java.time.ZonedDateTime;

public record Post(Long postId, String body, ZonedDateTime createdDateTime) {
//    public static Post(PostEntity postEntity) {
//        this(postEntity.getPostId(), postEntity.getBody(), postEntity.getCreatedDateTime());
//    }
}
