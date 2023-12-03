package com.example.demo;

import java.util.Optional;
import java.util.List;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository used to declare methods to use with UserRecords.
 */
@Repository
public interface UserRepository extends DatastoreRepository<UserRecord, Long> {

    /**
     * finds a userRecord by userName.
     * @param userName: manually entered String by the user
     * @return UserRecord that matches the userName param
     */
    Optional<UserRecord> findByUserName(String userName);

}