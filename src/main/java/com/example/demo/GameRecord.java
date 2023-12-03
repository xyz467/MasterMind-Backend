package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Entity(name = "games")
public class GameRecord {
  @Id
  Long id;

  int score;

  String date;


  String googleId;

  public GameRecord(int score, String date, String googleId) {
    this.score = score;
    this.date = date;
    this.googleId = googleId;
  }

  public long getId() {
    return this.id;
  }
  
  public void setId(Long id) {
  	this.id=id;
  }

  public void setScore(int score) {

    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  public String getDate() {
    return this.date;
  }

  public void setDate(String date) {
    this.date=date;
  }

  public String getGoogleId() {
    return this.googleId;
  }


  @Override
  public String toString() {
    return "{" +
            "id:" + this.id +
            ", googleId:'" + this.googleId + '\'' +
            ", Score:" + this.score +
            ", date:" + this.date +
            '}';
  }
}