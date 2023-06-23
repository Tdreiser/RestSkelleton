package ru.nordclan.RestSkelleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nordclan.RestSkelleton.entity.User;

import java.util.Optional;

/**
 * @author Shlokov Andrey
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
}