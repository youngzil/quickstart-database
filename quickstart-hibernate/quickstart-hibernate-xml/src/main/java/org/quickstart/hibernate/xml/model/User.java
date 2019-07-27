package org.quickstart.hibernate.xml.model;

import java.util.Date;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-07-27 16:54
 */
public class User {

  private int id;

  private String name;

  private int age;

  private String sex;

  private Date birthday;

  private String address;


  public User() {
  }

  public User(int id, String name, int age, String sex, Date birthday, String address) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.birthday = birthday;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", age=" + age
        + ", sex=" + sex + ", birthday=" + birthday + ", address="
        + address + "]";
  }

}