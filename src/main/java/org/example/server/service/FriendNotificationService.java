package org.example.server.service;

import org.example.server.models.FriendNotification;
import org.example.server.repositories.FriendNotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendNotificationService {
    private final FriendNotificationRepository friendNotificationRepository;

    public FriendNotificationService(FriendNotificationRepository friendNotificationRepository) {
        this.friendNotificationRepository = friendNotificationRepository;
    }
    public FriendNotification creatNotification(FriendNotification friends){
        FriendNotification Friends = friendNotificationRepository.save(friends);
        friendNotificationRepository.flush();
        return Friends;
    }

    public FriendNotification friendshipExists(Long userId, Long friendId) {
        return friendNotificationRepository.findByUserIdAndFriendId(userId, friendId);
    }

    public void deleteFriends(Long userId, Long friendId){
        friendNotificationRepository.deleteByUserIdAndFriendId(userId, friendId);
        friendNotificationRepository.flush();
    }
}
