package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.choonz.persistence.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
