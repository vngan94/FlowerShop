package com.tttn.flowershop.model;

import com.tttn.flowershop.utils.Const;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class User {
  private int id;
  @NotBlank(message = "Name cannot be blank")
  private String fullName;
  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email invalidate")
  private String email;
  @NotBlank(message = "Phone Number cannot be blank")
  @Pattern(regexp = Const.Regex.regPhone,message = "PhoneNumber is not illegal")
  private String phone;
  private String address;
  @NotBlank(message = "Password cannot be blank")
  private String password;
  private Boolean isDeleted;
  private Integer idRole;
  private String roleName;
  private Long loyaltyPoint;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setIsDeleted(Boolean deleted) {
    isDeleted = deleted;
  }

  public Integer getIdRole() {
    return idRole;
  }

  public void setIdRole(Integer idRole) {
    this.idRole = idRole;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Long getLoyaltyPoint() {
    return loyaltyPoint;
  }

  public void setLoyaltyPoint(Long loyaltyPoint) {
    this.loyaltyPoint = loyaltyPoint;
  }
}
