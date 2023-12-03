package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

/**
 * UserRecord entity with data members googleId and userName
 * googleId will be automatically generated from firebase when the user authenticates
 * userName will be manually inputted by the user before the user can play
 */
@Entity(name = "users")
public class UserRecord {

    @Id
    String googleId;
    String userName;

    /**
     * constructor
     * @param googleId: will be automatically generated from firebase when the user authenticates
     * @param userName: will be manually inputted by the user before the user can play
     */
    public UserRecord(String googleId, String userName){
        this.googleId = googleId;
        this.userName = userName;
    }

    /**
     * sets the googleId
     * @param googleId: unique string from fireBase
     */
    public void setGoogleId(String googleId){ this.googleId = googleId;}

    /**
     * gets the user's google Id
     * @return googleId: unqiue string for user from fireBase
     */
    public String getGoogleId() {
        return this.googleId;
    }

    /**
     * sets the user's userName
     * @param userName: manually inputted string for the user
     */
    public void setUserName(String userName) {
        this.userName= userName;
    }

    /**
     * gets the user's userName
     * @return String userName
     */
    public String getUserName() {
        return this.userName;
    }


    /**
     * toString method
     * @return String googleId + userName
     */
    @Override
    public String toString() {
        return "{" +
                ", googleId:'" + this.googleId + '\'' +
                ", userName:'" + this.userName + '\'' +
                '}';
    }
}
