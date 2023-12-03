package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * GameRepository used to declare methods to use with GameRecord entity
 */
public interface GameRepository extends DatastoreRepository<GameRecord, Long> {

  /**
   * findAll GameRecords
   * @param pageable: used to determine how many gameRecords should be returned with each page
   * @return Pages of GameRecords
   */
  Page<GameRecord> findAll(Pageable pageable);

  /**
   * finds gameRecords by date
   * @param date: date the game was played
   * @return a list of GameRecords
   */
  List<GameRecord> findByDate(String date);

  /**
   * finds gameRecords by googldId
   * @param googleId: unique user id from firebase
   * @return a list of GameRecords
   */
  List<GameRecord> findByGoogleId(String googleId);

  /**
   * find a gameRecord by using all of it's data members
   * @param score: score of the game played
   * @param date: date the game was played
   * @param googleId: unique id of the player from firebase
   * @return optional GameRecord
   */
  Optional<GameRecord> findByScoreAndDateAndGoogleId(int score, String date, String googleId);
}