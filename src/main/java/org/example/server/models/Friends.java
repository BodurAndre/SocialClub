package org.example.server.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FRIENDS")
public class Friends {

    @Id
    @Column(name ="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "FRIEND_ID")
    private Long friendId;

    public Friends (Long userId, Long friendId){
        this.userId = userId;
        this.friendId = friendId;
    }

}
