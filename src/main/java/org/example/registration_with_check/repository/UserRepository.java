package org.example.registration_with_check.repository;

import org.example.registration_with_check.model.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT user FROM UserEntity user WHERE user.valid IS NULL")
    List<UserEntity> getAllUnprocessedUser();

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity user SET user.valid = true WHERE user.id = :id")
    void userIsProcessed(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity user SET user.sendMail = true WHERE user.id = :id")
    void mailIsSended(@Param("id") Long id);
}
