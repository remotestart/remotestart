package com.capstone.remotestart.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private List<Project> projects;

    //mapping table SOURCE https://stackoverflow.com/questions/42488559/manytomany-relationship-between-three-tables
    @JsonBackReference
    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserTeamRoleLink> userTeamRoleLinks;

//Constructors

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    //Getters and Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Set<UserTeamRoleLink> getUserTeamRoleLinks() {
        return userTeamRoleLinks;
    }

    public void setUserTeamRoleLinks(Set<UserTeamRoleLink> userTeamRoleLinks) {
        this.userTeamRoleLinks = userTeamRoleLinks;
    }
}
