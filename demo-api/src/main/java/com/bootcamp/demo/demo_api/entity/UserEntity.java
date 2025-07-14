package com.bootcamp.demo.demo_api.entity;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private Long jphUserId;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false, name = "user_name")
  private String username;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String phone;
  @Column(nullable = false)
  private String website;
}