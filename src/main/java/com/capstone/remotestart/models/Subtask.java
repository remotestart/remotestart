package com.capstone.remotestart.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sub_tasks")
public class Subtask {

    private long id;

    private String title;

    private String description;

    private long taskId;

    private long stateId;

//Constructors
    public Subtask() {
    }

//Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }
}
