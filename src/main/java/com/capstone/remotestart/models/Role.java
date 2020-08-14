package com.capstone.remotestart.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private long id;

    @Column(length = 50, nullable = false)
    private String role;

    //mapping table SOURCE https://stackoverflow.com/questions/42488559/manytomany-relationship-between-three-tables
    @OneToMany(mappedBy = "role", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserTeamRoleLink> userTeamRoleLinks;

    public Role() {
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<UserTeamRoleLink> getUserTeamRoleLinks() {
        return userTeamRoleLinks;
    }

    public void setUserTeamRoleLinks(Set<UserTeamRoleLink> userTeamRoleLinks) {
        this.userTeamRoleLinks = userTeamRoleLinks;
    }
}
