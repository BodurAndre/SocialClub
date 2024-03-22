package org.example.server.repositories;

import jakarta.transaction.Transactional;
import org.example.server.models.FriendNotification;
import org.example.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendNotificationRepository extends JpaRepository<FriendNotification,Long> {
    @Query("SELECT f FROM FriendNotification f WHERE f.userId = ?1 AND f.friendId = ?2")
    FriendNotification findByUserIdAndFriendId(Long userId, Long friendId);

    @Query("SELECT f.userId FROM FriendNotification f WHERE f.friendId = :id")
    List<Long> findFriendIdsByUserId(@Param("id") Long id);
    @Transactional
    @Modifying
    @Query("DELETE FROM FriendNotification f WHERE (f.userId = ?1 AND f.friendId = ?2) OR (f.userId = ?2 AND f.friendId = ?1)")
    void deleteByUserIdAndFriendId(Long userId1, Long userId2);


}
