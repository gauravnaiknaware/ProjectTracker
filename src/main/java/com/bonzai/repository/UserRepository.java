package com.bonzai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bonzai.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
