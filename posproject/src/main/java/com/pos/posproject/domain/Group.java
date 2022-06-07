package com.pos.posproject.domain;

public class Group {
    private Integer group_code;
    private String group_title;
    private String group_explain;
    private String group_master_id;
    private Integer group_master_code;

    public Group(String group_title, String group_explain, Integer group_master_code, String group_master_id) {
        this.group_title = group_title;
        this.group_explain = group_explain;
        this.group_master_code = group_master_code;
        this.group_master_id = group_master_id;
    }

    public Integer getGroup_code() {
        return group_code;
    }

    public void setGroup_code(Integer group_code) {
        this.group_code = group_code;
    }

    public String getGroup_title() {
        return group_title;
    }

    public void setGroup_title(String group_title) {
        this.group_title = group_title;
    }

    public String getGroup_explain() {
        return group_explain;
    }

    public void setGroup_explain(String group_explain) {
        this.group_explain = group_explain;
    }

    public Integer getGroup_master_code() {
        return group_master_code;
    }

    public void setGroup_master_code(Integer group_master_code) {
        this.group_master_code = group_master_code;
    }

    public String getGroup_master_id() {
        return group_master_id;
    }

    public void setGroup_master_id(String group_master_id) {
        this.group_master_id = group_master_id;
    }
}