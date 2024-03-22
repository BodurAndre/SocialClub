package org.example.server.service;

import jakarta.transaction.Transactional;
import org.example.server.models.FriendNotification;
import org.example.server.models.Friends;
import org.example.server.repositories.FriendNotificationRepository;
import org.example.server.repositories.FriendRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository, FriendNotificationRepository friendNotificationRepository) {
        this.friendRepository = friendRepository;
    }

    public Friends createFriends(Friends friends){
        Friends newFriends = friendRepository.save(friends);
        friendRepository.flush();
        return newFriends;
    }


    public void deleteFriends(Long userId, Long friendId){
        friendRepository.deleteByUserIdAndFriendId(userId, friendId);
        friendRepository.flush();
    }

    public boolean friendshipExists(Long userId, Long friendId) {
        return friendRepository.existsByUserIdAndFriendIdOrFriendIdAndUserId(userId, friendId);
    }

}
