package com.grs.demo.annotation.demo;

//定义一个bean类，类和字段加注解
@Table("user")
public class UserBean {
 @Column("id")
 private int id;
 @Column("username")
 private String username;
 @Column("nickname")
 private String nickname;
 @Column("age")
 private int age;
 @Column("city")
 private String city;
 @Column("email")
 private String email;
 @Column("mobile")
 private String mobile;

 public int getId() {
 return id;
 }

 public void setId(int id) {
 this.id = id;
 }

 public String getUsername() {
 return username;
 }

 public void setUsername(String username) {
 this.username = username;
 }

 public String getNickname() {
 return nickname;
 }

 public void setNickname(String nickname) {
 this.nickname = nickname;
 }

 public int getAge() {
 return age;
 }

 public void setAge(int age) {
 this.age = age;
 }

 public String getCity() {
 return city;
 }

 public void setCity(String city) {
 this.city = city;
 }

 public String getEmail() {
 return email;
 }

 public void setEmail(String email) {
 this.email = email;
 }

 public String getMobile() {
 return mobile;
 }

 public void setMobile(String mobile) {
 this.mobile = mobile;
 }
}