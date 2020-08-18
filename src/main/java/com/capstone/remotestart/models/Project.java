package com.capstone.remotestart.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(columnDefinition = "DATE", nullable = false)
    private String startDate;

    @Column(columnDefinition = "DATE", nullable = false)
    private String deadline;

    @Column(columnDefinition = "DATE")
    private String completionDate;

    //relation to team
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    //relation to task
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<Task> task;

//Constructors
    public Project(){}

// Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
