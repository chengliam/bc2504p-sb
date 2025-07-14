package com.bootcamp.demo.demo_api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_api.entity.PostEntity;
import com.bootcamp.demo.demo_api.entity.UserEntity;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  // ! Jpa Method returns:
  // 1. Optional<PostEntity>
  // 2. PostEntity
  // 3. List<PostEntity>

  Optional<PostEntity> findByJphPostId(Long jphPostId);

  // ! Important (select by FK)
  List<PostEntity> findByUserEntity(UserEntity userEntity);

  List<PostEntity> findByTitle(String title);
}