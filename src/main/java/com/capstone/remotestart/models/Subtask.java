package com.capstone.remotestart.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "sub_tasks")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    //relation to task
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    //relation to subtask
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateOfCompletion stateOfCompletion;


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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public StateOfCompletion getStateOfCompletion() {
        return stateOfCompletion;
    }

    public void setStateOfCompletion(StateOfCompletion stateOfCompletion) {
        this.stateOfCompletion = stateOfCompletion;
    }
}
