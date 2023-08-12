package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;

public class PostAccountPayLoad extends APIRequest {

    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private Integer idRole;
    private Long loyaltyPoint;

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

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Long getLoyaltyPoint() {
        return loyaltyPoint;
    }

    public void setLoyaltyPoint(Long loyaltyPoint) {
        this.loyaltyPoint = loyaltyPoint;
    }
}
