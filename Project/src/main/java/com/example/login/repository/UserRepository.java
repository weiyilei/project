package com.example.login.repository;

import com.example.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//modified


//通过数据库把数据查询出来
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByTelephone(String telephone);
    User findById(String id);

}
