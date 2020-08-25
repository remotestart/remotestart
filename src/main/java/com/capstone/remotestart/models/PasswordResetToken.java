package com.capstone.remotestart.models;

import com.capstone.remotestart.models.User;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String passwordResetToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public PasswordResetToken(User user) {
        this.user = user;
        createdDate = new Date();
        passwordResetToken = UUID.randomUUID().toString();
    }

    // getters and setters
    public PasswordResetToken(long tokenid, String passwordResetToken, Date createdDate, User user) {
        this.tokenid = tokenid;
        this.passwordResetToken = passwordResetToken;
        this.createdDate = createdDate;
        this.user = user;
    }

    public PasswordResetToken(String passwordResetToken, Date createdDate, User user) {
        this.passwordResetToken = passwordResetToken;
        this.createdDate = createdDate;
        this.user = user;
    }

    public PasswordResetToken() {
    }

    public long getTokenid() {
        return tokenid;
    }

    public void setTokenid(long tokenid) {
        this.tokenid = tokenid;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}