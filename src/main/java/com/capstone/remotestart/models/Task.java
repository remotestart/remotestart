package com.capstone.remotestart.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    //relation to project
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    //relation to user (assigned to)
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //relation to state_of_completion
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "state_id")
    private StateOfCompletion stateOfCompletion;

    //relation to subtask
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<Subtask> subtasks;

    //Constructors
    public Task() {}


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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StateOfCompletion getStateOfCompletion() {
        return stateOfCompletion;
    }

    public void setStateOfCompletion(StateOfCompletion stateOfCompletion) {
        this.stateOfCompletion = stateOfCompletion;
    }

    public List<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}