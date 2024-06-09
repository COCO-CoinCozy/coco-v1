package com.danghouse.cocov1.repository;

import com.danghouse.cocov1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}

