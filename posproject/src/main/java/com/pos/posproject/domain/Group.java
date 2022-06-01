package com.pos.posproject.domain;

public class Group {
    private Long code;
    private int writer_code;
    private String writer_id;
    private String title;
    private String explain;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getWriter_code() {
        return writer_code;
    }

    public void setWriter_code(int writer_code) {
        this.writer_code = writer_code;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
