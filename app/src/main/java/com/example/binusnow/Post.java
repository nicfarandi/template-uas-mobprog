package com.example.binusnow;

public class Post {
    private String studentName;
    private String content;

    public Post(String studentName, String content) {
        this.studentName = studentName;
        this.content = content;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getContent() {
        return content;
    }
}