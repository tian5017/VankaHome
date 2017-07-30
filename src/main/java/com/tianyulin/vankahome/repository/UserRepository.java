package com.tianyulin.vankahome.repository;

import com.tianyulin.vankahome.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tianyulin on 2017/7/25.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from vk_user where user_name=?1 and password=?2 limit 1", nativeQuery = true)
    User findUser(String userName, String password);

    @Query(value = "select * from vk_user where user_name=?1 limit 1", nativeQuery = true)
    User findUserByName(String userName);
}
