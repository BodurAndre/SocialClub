package org.example.server.repositories;

import jakarta.transaction.Transactional;
import org.example.server.models.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friends,Long> {

    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Friends f WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    boolean existsByUserIdAndFriendIdOrFriendIdAndUserId(Long userId1, Long userId2);

    @Transactional
    @Modifying
    @Query("DELETE FROM Friends f WHERE f.userId = ?1 AND f.friendId = ?2")
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
