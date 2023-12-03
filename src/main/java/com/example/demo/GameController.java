package com.example.demo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * GameController for User and Game Repositories
 */
@RestController
public class GameController {
  private final GameRepository gameRepository;
  private final UserRepository userRepository;

  public GameController(GameRepository gameRepository, UserRepository userRepository) {
    this.gameRepository = gameRepository;
    this.userRepository = userRepository;
  }

  /**
   * saves a gameRecord
   * @param game: type GameRecord with data members: score, date, googleId
   * @return String stating if the save was successful or not. Will save the gameRecord into the database if successful
   */
  @PostMapping("/saveGame")
  @CrossOrigin(origins = "*")
  public String saveGame(@RequestBody GameRecord game) {
    if (game == null) {
      return "The game is invalid";
    }
    this.gameRepository.save(game);
    return "success";
  }

  /**
   * saves a userRecord
   * @param userRecord: type UserRecord with data members: googleId, userName
   * @return String stating if the save was successful or not. Will save the userRecord into the database if successful.
   */
  @PostMapping("/saveUser")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public String saveUserRecord(@RequestBody UserRecord userRecord) {
    if (userRecord == null) {
      return "The userRecord is invalid";
    }
    this.userRepository.save(userRecord);
    return "success";
  }

  /**
   * findAllGames() using pagination and sorting to find the top 5 scores out of all gameRecords
   * @return >5 gameRecords that had the top 5 scores in the database
   */
  @GetMapping("/findAllGames")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public Page<GameRecord> findAllGames() {
    Pageable pageable = PageRequest.of(0, 5, Sort.by("score").descending());
    return gameRepository.findAll(pageable);
  }

  /**
   * finds gameRecords by date
   * @param date: in YYYY-MM-DD format, type String
   * @return a list of GameRecords that contain the same date as the param
   */
  @GetMapping("/findByDate")
  @CrossOrigin(origins = "*")
  public List<GameRecord> findByDate(@RequestParam String date){
    return gameRepository.findByDate(date);
  }

  /**
   * find gameRecords by the user's googleId
   * @param googleId: unique id of user that played the game, from firebase
   * @return list of GameRecords that were played by the googleId
   */
  @GetMapping("/findGamesByGoogleId/{googleId}")
  @CrossOrigin(origins = "*")
  public List<GameRecord> findByGoogleId(@PathVariable String googleId) {
    Sort sort = Sort.by("score").descending();
    return gameRepository.findByGoogleId(googleId);
  }

  /**
   * finds gameRecords by userName. It first finds the userRecord in the userRepository by searching by userName
   * it then finds the googleId associated with that userName. It then uses the googleId to find all
   * gameRecords associated with that googleId, which is also associated with the userName
   * @param userName: user's manually inputted userName of type String
   * @return a list of GameRecods that were played by the userName
   */
  @GetMapping("/findByUserName/{userName}")
  @CrossOrigin(origins = "*")
  public List<GameRecord> findGameRecordsByUsername(@PathVariable String userName) {
    Optional<UserRecord> userRecordOpt = userRepository.findByUserName(userName);

    if (userRecordOpt.isPresent()) {
      UserRecord userRecord = userRecordOpt.get();
      String googleId = userRecord.getGoogleId(); // Ensure this method is public and returns a String

      if (googleId != null) {
        return gameRepository.findByGoogleId(googleId);
      }
    }
    return Collections.emptyList();
  }

  /**
   * deletes a Game Record that matches the requested params. It will delete the first match if there are multiple exacts.
   * @param score: type int, the score of the game
   * @param date: type String, the date the game was played
   * @param googleId: type String, unique id from firebase for the user
   * @return String stating if the deletion was successful or not. Will delete the GameRecord from the database if it was found.
   * Will only delete one gameRecord.
   */
  @DeleteMapping("/deleteGameRecord")
  @CrossOrigin(origins = "*")
  public String deleteGameRecord(@RequestParam int score, @RequestParam String date, @RequestParam String googleId) {
    Optional<GameRecord> recordOpt = gameRepository.findByScoreAndDateAndGoogleId(score, date, googleId);
    //using optional because I only want it to delete one gameRecord if multiple exacts exist.
    if (recordOpt.isPresent()) {
      gameRepository.delete(recordOpt.get());
      return "Game record deleted successfully";
    } else {
      return "Game record not found";
    }
  }

}