package com.example.demo;

import java.util.List;
import java.util.Optional;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


public interface GameRepository extends DatastoreRepository<GameRecord, Long> {

  Page<GameRecord> findAll(Pageable pageable);

  List<GameRecord> findByDate(String date);


  List<GameRecord> findByGoogleId(String googleId);

  Optional<GameRecord> findByScoreAndDateAndGoogleId(int score, String date, String googleId);
}