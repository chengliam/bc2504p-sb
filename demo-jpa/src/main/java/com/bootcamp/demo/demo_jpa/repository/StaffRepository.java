package com.bootcamp.demo.demo_jpa.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;

// ! Select Update Insert Delete (CRUD)
@Repository // Bean
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
  // ! Hibernate (Framework)

  // select * from staffs;
  // insert into staffs values (xxx,xxx...);
  // simliar to update, delete

  // ! 1. JPA Methods - pre-defined method structure -> SQL
  // ! findByXXX -> Optional or List
  // select * from staffs where name = ?
  Optional<StaffEntity> findByName(String name);

  // Jpa Support and or
  // select * from staffs where name = ? and email = ?
  List<StaffEntity> findByNameAndEmail(String name, String email);

  // return List

  // ! 2. JPQL (Java Persistence Query Language)
  
  // ! 3. Native Query (Normal SQL). Not a good idea for simple SQL. SQL is Product specific.
}