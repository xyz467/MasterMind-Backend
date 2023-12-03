package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;
@Entity(name = "users")
public class UserRecord {

    @Id
    String googleId;
    String userName;

    public UserRecord(String googleId, String userName){
        this.googleId = googleId;
        this.userName = userName;
    }

    public void setGoogleId(String googleId){ this.googleId = googleId;}

    public String getGoogleId() {
        return this.googleId;
    }

    public void setUserName(String userName) {
        this.userName= userName;
    }

    public String getUserName() {
        return this.userName;
    }



    @Override
    public String toString() {
        return "{" +
                ", googleId:'" + this.googleId + '\'' +
                ", userName:'" + this.userName + '\'' +
                '}';
    }
}
