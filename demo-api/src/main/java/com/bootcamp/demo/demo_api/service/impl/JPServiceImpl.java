package com.bootcamp.demo.demo_api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.demo_api.entity.CommentEntity;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.exception.BusinessException;
import com.bootcamp.demo.demo_api.exception.SysError;
import com.bootcamp.demo.demo_api.lib.Scheme;
import com.bootcamp.demo.demo_api.mapper.EntityMapper;
import com.bootcamp.demo.demo_api.model.dto.CommentDTO;
import com.bootcamp.demo.demo_api.model.dto.PostDTO;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
import com.bootcamp.demo.demo_api.repository.CommentRepository;
import com.bootcamp.demo.demo_api.repository.PostRepository;
import com.bootcamp.demo.demo_api.repository.UserRepository;
import com.bootcamp.demo.demo_api.service.JPService;

@Service
public class JPServiceImpl implements JPService {
  // ! @Value has dependency, complete injection before server start completed.
  @Value("${service-api.jsonplaceholder.host}")
  private String domain;

  @Value("${service-api.jsonplaceholder.endpoints.users}")
  private String usersEndpoint;

  @Value("${service-api.jsonplaceholder.endpoints.posts}")
  private String postsEndpoint;

  @Value("${service-api.jsonplaceholder.endpoints.comments}")
  private String commentsEndpoint;

  @Autowired
  private RestTemplate restTemplate; // built-in library

  @Autowired
  @Qualifier(value = "student") // explicit bean name
  private String abc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private EntityMapper entityMapper;

  @Override
  public List<UserDTO> getUsers() {
    // String url = "https://" + domain + usersEndpoint;
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Scheme.HTTPS.getValue()) //
        .host(domain) //
        .path(usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);

    // ! getForObject():
    // 1. call API, returns JSON String
    // 2. convert from JSON String to Java Object
    UserDTO[] users = this.restTemplate.getForObject(url, UserDTO[].class);
    return Arrays.asList(users);
  }

  @Override
  public List<UserEntity> getAndSaveUsers() {
    // ! Convert List<UserDTO> to List<UserEntity>
    List<UserEntity> userEntities = this.getUsers().stream() //
        .map(e -> this.entityMapper.map(e)) //
        .collect(Collectors.toList());
    this.userRepository.deleteAll();
    // save to DB
    return this.userRepository.saveAll(userEntities);
  }

  @Override
  public List<PostEntity> getAndSavePosts() {
    List<PostEntity> postEntities = this.getPosts().stream() //
        .map(e -> {
          UserEntity userEntity =
              this.userRepository.findByJphUserId(e.getUserId())
                  .orElseThrow(() -> new BusinessException(SysError.USER_NOT_FOUND));
          return this.entityMapper.map(e, userEntity);
        }).collect(Collectors.toList());
    return this.postRepository.saveAll(postEntities);
  }

  @Override
  public List<CommentEntity> getAndSaveComments() {
    List<CommentEntity> commentEntities = this.getComments().stream() //
        .map(e -> {
          PostEntity postEntity =
              this.postRepository.findByJphPostId(e.getPostId())
                  .orElseThrow(() -> new RuntimeException("Post not found."));
          return this.entityMapper.map(e, postEntity);
        }).collect(Collectors.toList());
    return this.commentRepository.saveAll(commentEntities);
  }

  private List<CommentDTO> getComments() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Scheme.HTTPS.getValue()) //
        .host(domain) //
        .path(commentsEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    CommentDTO[] commentDTOs =
        this.restTemplate.getForObject(url, CommentDTO[].class);
    return Arrays.asList(commentDTOs);
  }

  private List<PostDTO> getPosts() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme(Scheme.HTTPS.getValue()) //
        .host(domain) //
        .path(postsEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    PostDTO[] users = this.restTemplate.getForObject(url, PostDTO[].class);
    return Arrays.asList(users);
  }

  @Override
  public List<PostEntity> getPostsByUserId(Long userId) {
    UserEntity userEntity = this.userRepository.findById(userId) //
        .orElseThrow(() -> new RuntimeException("User not found."));
    return this.postRepository.findByUserEntity(userEntity);
  }

  @Override
  public List<UserEntity> findAllUsers() {
    return this.userRepository.findAll();
  }
}