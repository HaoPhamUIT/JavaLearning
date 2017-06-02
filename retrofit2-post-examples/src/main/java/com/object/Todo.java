package com.object;

/**
 * Created by my-tran on 5/26/17.
 */
public class Todo {
    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public Todo(int i, int i1, String s, boolean b) {
        this.id= i;
        this.userId= i1;
        this.setTitle(s);
        this.completed= b;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
