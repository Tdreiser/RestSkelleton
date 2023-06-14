package ru.nordclan.RestSkelleton.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nordclan.RestSkelleton.entity.Client;

import java.util.List;

/**
 * @author Shlokov Andrey
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "select name from clients cl where cl.name like ?1%", nativeQuery = true)
    List<String> findByClientNameStartsWith(String prefix);

}
