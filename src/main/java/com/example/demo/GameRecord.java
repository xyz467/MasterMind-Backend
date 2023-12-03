package com.example.demo;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

/**
 * GameRecord entity used to store entries of gameRecords after a game of Mastermind is completed
 */
@Entity(name = "games")
public class GameRecord {
  @Id
  Long id;

  int score;

  String date;


  String googleId;

  /**
   * GameRecord constructor
   * @param score: user's score during this game record entry of Mastermind
   * @param date: the date that the game was completed
   * @param googleId: user's unique googleId from firebase
   */
  public GameRecord(int score, String date, String googleId) {
    this.score = score;
    this.date = date;
    this.googleId = googleId;
  }

  /**
   * get unique id of gameRecord
   * @return long ID
   */
  public long getId() {
    return this.id;
  }

  /**
   * set unique id of gameRecord
   * @param id: unique Long id
   */
  public void setId(Long id) {
  	this.id=id;
  }

  /**
   * set score of game played to be stored in gameRecord
   * @param score: score of Mastermind game played
   */
  public void setScore(int score) {

    this.score = score;
  }

  /**
   * get score of game played in gameRecord
   * @return int score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * get date of game played
   * @return String date played
   */
  public String getDate() {
    return this.date;
  }

  /**
   * set date of game played
   * @param date: date the game was played
   */
  public void setDate(String date) {
    this.date=date;
  }

  /**
   * get googleId of user that played the game
   * @return string googleId of user that played
   */
  public String getGoogleId() {
    return this.googleId;
  }


  /**
   * toString function
   * @return String id + googleId + score + date
   */
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