package com.bootcamp.demo.demo_api.model.dto;

import lombok.Getter;

@Getter
public class CommentDTO {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}