package com.sszh.server.sso.bean;

import com.sszh.common.bean.CommonEntry;

/**
 * 用户实体
 */
public class User extends CommonEntry {

    private Long id;
    private String username;
    private String name;
    private Integer age;
    private Double balance;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
