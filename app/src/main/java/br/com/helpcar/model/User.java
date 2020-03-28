package br.com.helpcar.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int userId = 0;
    private String userName;
    private int userCpf;
    private String userEmail;

    public User(int userId, String userName, int userCpf, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userCpf = userCpf;
        this.userEmail = userEmail;
    }

    public User() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(int userCpf) {
        this.userCpf = userCpf;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
