package com.example.demo.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoleType {
    TENANT("tenant"),
    ADMIN("admin");
    private final String role;

    RoleType(String role){
        this.role = role;
    }

    @JsonValue
    public String getRole(){
        return  role;
    }
    //this makes values of enum in json passed insensitive
    @JsonCreator
    public static RoleType fromValue(String role){
        for(RoleType roleType: values()){
            if(roleType.role.equalsIgnoreCase(role)){
                return roleType;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType");
    }

}
