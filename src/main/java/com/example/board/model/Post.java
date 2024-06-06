package com.example.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter @Setter
@AllArgsConstructor
public class Post {
    private Long postId;
    private String body;
    private ZonedDateTime createdDateTime;
}
