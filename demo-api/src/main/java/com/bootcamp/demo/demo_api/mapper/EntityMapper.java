package com.bootcamp.demo.demo_api.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.CommentDTO;
import com.bootcamp.demo.demo_api.model.dto.PostDTO;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

@Component // Custom class -> Bean
public class EntityMapper {
  public PostEntity map(PostDTO postDTO, UserEntity userEntity) {
    return PostEntity.builder() //
        .jphPostId(postDTO.getId()) //
        .title(postDTO.getTitle()) //
        .body(postDTO.getBody()) //
        .userEntity(userEntity) //
        .build();
  }

  public CommentEntity map(CommentDTO commentDTO, PostEntity postEntity) {
    return CommentEntity.builder() //
        .name(commentDTO.getName()) //
        .email(commentDTO.getEmail()) //
        .body(commentDTO.getBody()) //
        .postEntity(postEntity) //
        .build();
  }

  public UserEntity map(UserDTO userDTO) {
    return UserEntity.builder() //
        .jphUserId(userDTO.getId()) //
        .email(userDTO.getEmail()) //
        .phone((userDTO.getPhone())) //
        .username(userDTO.getUsername()) //
        .website(userDTO.getWebsite()) //
        .name(userDTO.getName()) //
        .build();
  }
}