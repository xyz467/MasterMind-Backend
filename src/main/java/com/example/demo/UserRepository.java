package com.example.demo;

import java.util.Optional;
import java.util.List;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends DatastoreRepository<UserRecord, Long> {

    Optional<UserRecord> findByUserName(String userName);

    //   Optional<GameRecord> findByGoogleId(String googleId);
}