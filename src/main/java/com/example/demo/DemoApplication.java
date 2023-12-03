package com.example.demo;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * DemoApplication to run Spring Boot locally
 */
@ShellComponent
@SpringBootApplication
public class DemoApplication {
  @Autowired
  GameRepository gameRepository;
  UserRepository userRepository;

  public static void main(String[] args) {
     SpringApplication.run(DemoApplication.class, args);
  }

    /**
     * saves a gameRecord
     * @param score: score of the game played
     * @param date: date game was played
     * @param googleId: unique id of user from firebase
     * @return String of savedGame Record toString
     */
  @ShellMethod("Saves a game to Cloud Datastore: save-game <score> <date> <googleId>")
  public String saveGame(int score, String date, String googleId) {
     GameRecord savedGame = this.gameRepository.save(new GameRecord(score, date, googleId));
     return savedGame.toString();
  }

    /**
     * save a userRecord
     * @param googleId: unique id of user from firebase
     * @param userName: manually entered userName of user
     * @return String of savedUser Record toString
     */
  @ShellMethod("Saves a user to Cloud Datastore: save-user <googleId> <userName>")
  public String saveUser(String googleId, String userName) {
      UserRecord savedUser = this.userRepository.save(new UserRecord(googleId, userName));
      return savedUser.toString();
  }

    /**
     * Finds all gameRecords in the database.
     * @return List of all GameRecords in the database
     */
  @ShellMethod("Loads all games")
  public String findAllGames() {
     Iterable<GameRecord> games = this.gameRepository.findAll();
     return Lists.newArrayList(games).toString();
  }

    /**
     * finds gameRecords by date played
     * @param date: date game was played
     * @return list of GameRecords toString
     */
  @ShellMethod("Loads games by date: find-by-date <date>")
  public String findByDate(String date) {
     List<GameRecord> games = this.gameRepository.findByDate(date);
     return games.toString();
  }


    /**
     * deletes all gameRecords in the dataBase.
     */
  @ShellMethod("Removes all gameRecords")
  public void removeAllGames() {
     this.gameRepository.deleteAll();
  }
}
