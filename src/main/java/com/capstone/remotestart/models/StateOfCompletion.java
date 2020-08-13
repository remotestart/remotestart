package com.capstone.remotestart.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "state_of_completion")
public class StateOfCompletion {

    private long id;

    private String stateOfCompletion;

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
}
