package com.syj.mybatis.model;

/**
 * @author snailshen
 * @description  user model class represent
 * @create 2019-01-15 14:36
 */
public class User {

    public User(Integer id, String name, String password, Integer age, Integer deleteFlag) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", deleteFlag=" + deleteFlag +
                '}';
    }

    public User() {
    }

    private Integer id;
    private String name;
    private String password;
    private Integer age;
    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
