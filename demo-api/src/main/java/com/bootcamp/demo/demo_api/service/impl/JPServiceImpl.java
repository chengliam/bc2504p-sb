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
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;
import com.bootcamp.demo.demo_api.model.dto.PostDTO;
import com.bootcamp.demo.demo_api.model.dto.UserDTO;
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

  @Autowired
  private RestTemplate restTemplate; // built-in library

  @Autowired
  @Qualifier(value = "student") // explicit bean name
  private String abc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PostRepository postRepository;

  @Override
  public List<UserDTO> getUsers() {
    // String url = "https://" + domain + usersEndpoint;
    System.out.println("tutor=" + this.abc);

    String url = UriComponentsBuilder.newInstance() //
        .scheme("https").host(domain) //
        .path(usersEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    UserDTO[] users = this.restTemplate.getForObject(url, UserDTO[].class);
    return Arrays.asList(users);
  }

  @Override
  public List<UserEntity> getAndSaveUsers() {
    // ! Convert List<UserDTO> to List<UserEntity>
    List<UserEntity> userEntities = this.getUsers().stream() //
        .map(e -> {
          return UserEntity.builder() //
              .jphId(e.getId())
              .email(e.getEmail()) //
              .phone((e.getPhone())) //
              .username(e.getUsername()) //
              .website(e.getWebsite()) //
              .name(e.getName()) //
              .build();
        }).collect(Collectors.toList());
    this.userRepository.deleteAll();
    // save to DB
    return this.userRepository.saveAll(userEntities);
  }

  @Override
  public List<PostEntity> getAndSavePosts() {
    List<PostEntity> postEntities = this.getPosts().stream() //
        .map(e -> {
          System.out.println("userid" + e.getUserId());
          
          UserEntity userEntity = this.userRepository.findByJphId(e.getUserId())
              .orElseThrow(() -> new RuntimeException("User not found."));
          return PostEntity.builder() //
              .title(e.getTitle()) //
              .body(e.getBody()) //
              .userEntity(userEntity) // ! set FK
              .build();
        }).collect(Collectors.toList());
    return this.postRepository.saveAll(postEntities);
  }

  private List<PostDTO> getPosts() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme("https").host(domain) //
        .path(postsEndpoint) //
        .build() //
        .toUriString();
    System.out.println("url=" + url);
    PostDTO[] users = this.restTemplate.getForObject(url, PostDTO[].class);
    return Arrays.asList(users);
  }
}