package com.tttn.api.entities.request;

import com.tttn.api.entities.APIRequest;


public class UpdateGiftVoucherPayLoad extends APIRequest {

    private Integer id;
    private Boolean isUsed;
    private Boolean isDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getisUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean used) {
        this.isUsed = used;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
