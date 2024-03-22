package org.example.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FRIEND_NOTIFICATION")
public class FriendNotification {

    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FRIEND_ID")
    private Long friendId;

    @Column(name = "STATUS")
    private String status;

    public FriendNotification (Long userId, Long friendId, String status){
        this.userId = userId;
        this.friendId = friendId;
        this.status = status;
    }
}
