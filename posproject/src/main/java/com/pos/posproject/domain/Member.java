package com.pos.posproject.domain;

//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;



public class Member {

    private Integer code;
    private String name;
    private Integer studyTime;
    private String id;
    private String pwd;

    public Member() {
    }

    public Member(Integer code){
        this.code=code;
    }

    public Member(String name, String id, String pwd) {
        this.name = name;
        this.id = id;
        this.pwd = pwd;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
