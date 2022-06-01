package com.pos.posproject.domain;

import java.time.LocalDateTime;


public class Student {
    private Integer id;
    private String name;
    private String code;
    private LocalDateTime saveDate = LocalDateTime.now();

    public Student(String name, String code) {
        this.name=name;
        this.code=code;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate;
    }
}
