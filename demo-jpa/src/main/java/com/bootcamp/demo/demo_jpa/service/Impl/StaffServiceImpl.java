package com.bootcamp.demo.demo_jpa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.demo_jpa.entity.StaffEntity;
import com.bootcamp.demo.demo_jpa.repository.StaffRepository;
import com.bootcamp.demo.demo_jpa.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService {
  @Autowired
  private StaffRepository staffRepository;

  @Override
  public StaffEntity save(StaffEntity staffEntity) {
    return staffRepository.save(staffEntity); // ! insert into
  }

  @Override
  public Optional<StaffEntity> findById(Long id) {
    return this.staffRepository.findById(id);
  }

  @Override
  public List<StaffEntity> findAll() {
    return this.staffRepository.findAll();
  }

  @Override
  public Boolean deleteById(Long id) {
    if (!this.staffRepository.findById(id).isPresent())
      return false;
    this.staffRepository.deleteById(id);
    return true;
  }

  @Override
  public StaffEntity updateById(Long id, StaffEntity staffEntity) {
    if (!staffEntity.getId().equals(id)) // NPE
      throw new IllegalArgumentException("Wrong staff id.");
    if (!this.staffRepository.findById(id).isPresent())
      throw new IllegalArgumentException("id not exists.");
    return this.staffRepository.save(staffEntity);
  }

  @Override
  public StaffEntity updateNameById(Long id, String name) {
    StaffEntity staffEntity = this.staffRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("id not exists."));
    staffEntity.setName(name);
    return this.staffRepository.save(staffEntity);
  }
}