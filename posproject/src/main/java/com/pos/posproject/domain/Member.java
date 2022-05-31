package com.pos.posproject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {

    private int code;
    private String name;
    private int std_time;
    private String id;
    private String passwd;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getStd_time() {
        return std_time;
    }

    public void setStd_time(int std_time) {
        this.std_time = std_time;
    }
}
