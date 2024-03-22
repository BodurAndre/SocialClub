package org.example.server.repositories;

import org.example.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstname, '%'))")
    List<User> findUsersByPartialName(@Param("firstname") String firstname);
    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastname, '%'))")
    List<User> findUsersByPartialLast(@Param("lastname") String lastname);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    List<User> findUsersById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") Long id);
}

