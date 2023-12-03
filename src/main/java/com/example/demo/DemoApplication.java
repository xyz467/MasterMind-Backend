package com.example.demo;

import java.util.List;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@SpringBootApplication
public class DemoApplication {
  @Autowired
  GameRepository gameRepository;
  UserRepository userRepository;

  public static void main(String[] args) {
     SpringApplication.run(DemoApplication.class, args);
  }

  @ShellMethod("Saves a game to Cloud Datastore: save-game <score> <date> <googleId>")
  public String saveGameR(int score, String date, String googleId) {
     GameRecord savedGame = this.gameRepository.save(new GameRecord(score, date, googleId));
     return savedGame.toString();
  }

  @ShellMethod("Saves a user to Cloud Datastore: save-user <googleId> <userName>")
  public String saveUser(String googleId, String userName) {
      UserRecord savedUser = this.userRepository.save(new UserRecord(googleId, userName));
      return savedUser.toString();
  }

  @ShellMethod("Loads all books")
  public String findAllBooks() {
     Iterable<GameRecord> books = this.gameRepository.findAll();
     return Lists.newArrayList(books).toString();
  }

  @ShellMethod("Loads books by author: find-by-author <author>")
  public String findByDate(String date) {
     List<GameRecord> books = this.gameRepository.findByDate(date);
     return books.toString();
  }


  @ShellMethod("Removes all books")
  public void removeAllBooks() {
     this.gameRepository.deleteAll();
  }
}
