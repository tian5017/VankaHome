package com.tianyulin.vankahome.entity;

import javax.persistence.*;

/**
 * Created by tianyulin on 2017/5/23.
 * 人员(包含管理员和用户)
 */
@Entity
@Table(name="vk_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;
    //用户名
    @Column(name = "user_name")
    private String userName;
    //密码
    @Column(name = "password")
    private String password;
    //手机号
    @Column(name = "phone")
    private String phone;
    //用户角色（管理员admin,用户user）
    @Column(name = "user_role")
    private String userRole;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
