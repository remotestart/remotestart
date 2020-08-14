package com.capstone.remotestart.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "state_of_completion")
public class StateOfCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(length = 50, nullable = false)
    private String stateOfCompletion;

    //relation to task
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateOfCompletion")
    private List<Task> task;

    //relation to subtask
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateOfCompletion")
    private List<Subtask> subtasks;

//Constructors
    public StateOfCompletion() {
    }

    public StateOfCompletion(long id, String stateOfCompletion) {
        this.id = id;
        this.stateOfCompletion = stateOfCompletion;
    }

    public StateOfCompletion(String stateOfCompletion) {
        this.stateOfCompletion = stateOfCompletion;
    }

//Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStateOfCompletion() {
        return stateOfCompletion;
    }

    public void setStateOfCompletion(String stateOfCompletion) {
        this.stateOfCompletion = stateOfCompletion;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}
