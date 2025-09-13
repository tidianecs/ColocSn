package com.tidiane.ColocSn.Entity;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id @GeneratedValue private String userId;
    private String userName;
    private String userEmail;
    @OneToMany private List<Rent> ownRents = new ArrayList<>();

    User(){} //Empty contructor for jpa

    User(String userName, String userEmail, List<Rent> ownRents){
        this.userName = userName;
        this.userEmail = userEmail;
        this.ownRents = ownRents;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setOwnRents(List<Rent> ownRents) {
        this.ownRents = ownRents;
    }
    public List<Rent> getOwnRents() {
        return ownRents;
    }
}
