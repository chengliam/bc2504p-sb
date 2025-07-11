package com.bootcamp.demo.demo_api.service;

import java.util.List;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;

public interface JPService {
  List<UserDTO> getUsers();
  List<UserEntity> getAndSaveUsers();
  List<PostEntity> getAndSavePosts();
}