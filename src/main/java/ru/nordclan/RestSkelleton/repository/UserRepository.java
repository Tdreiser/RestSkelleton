package ru.nordclan.RestSkelleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nordclan.RestSkelleton.entity.User;

/**
 * @author Shlokov Andrey
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}