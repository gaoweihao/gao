package com.flowable.springboot.bean;

import com.flowable.springboot.common.BaseEntity;

public class RoleBean extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
