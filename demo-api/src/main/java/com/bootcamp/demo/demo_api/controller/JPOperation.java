package com.bootcamp.demo.demo_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

public interface JPOperation {
  @GetMapping(value = "/jph/users")
  List<UserDTO> getAllJPUsers();

  @PostMapping(value = "/jph/database/users")
  List<UserEntity> getAndSaveUsers();

  @PostMapping(value = "/jph/database/posts")
  List<PostEntity> getAndSavePosts();
}