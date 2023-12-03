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

@RestController
public class GameController {
  private final GameRepository gameRepository;
  private final UserRepository userRepository;

  public GameController(GameRepository gameRepository, UserRepository userRepository) {
    this.gameRepository = gameRepository;
    this.userRepository = userRepository;
  }

  @PostMapping("/saveGame")
  @CrossOrigin(origins = "*")
  public String saveGame(@RequestBody GameRecord game) {
    if (game == null) {
      return "The book is invalid";
    }
    this.gameRepository.save(game);
    return "success";
  }

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
  
  

  @GetMapping("/findAllGames")
  @ResponseBody
  @CrossOrigin(origins = "*")
  public Page<GameRecord> findAllGames() {
    Pageable pageable = PageRequest.of(0, 5, Sort.by("score").descending());
    return gameRepository.findAll(pageable);
  }

  @GetMapping("/findByDate")
  @CrossOrigin(origins = "*")
  public List<GameRecord> findByAuthor(@RequestParam String date){
    return gameRepository.findByDate(date);
  }

  @GetMapping("/findBooksByGoogleId/{googleId}")
  @CrossOrigin(origins = "*")
  public List<GameRecord> findBooksByUserId(@PathVariable String googleId) {
    Sort sort = Sort.by("score").descending();
    return gameRepository.findByGoogleId(googleId);
  }

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