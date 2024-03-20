package org.example.server.repositories;

import jakarta.transaction.Transactional;
import org.example.server.models.FriendNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendNotificationRepository extends JpaRepository<FriendNotification,Long> {
    @Query("SELECT f FROM FriendNotification f WHERE f.userId = ?1 AND f.friendId = ?2")
    FriendNotification findByUserIdAndFriendId(Long userId, Long friendId);
    @Transactional
    @Modifying
    @Query("DELETE FROM FriendNotification f WHERE f.userId = ?1 AND f.friendId = ?2")
    void deleteByUserIdAndFriendId(Long userId, Long friendId);
}
