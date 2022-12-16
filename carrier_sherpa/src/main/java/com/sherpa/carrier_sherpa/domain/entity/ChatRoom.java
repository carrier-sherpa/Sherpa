package com.sherpa.carrier_sherpa.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "chat_room")
@NoArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_room_id")
    private Long roomId;

    private String roomName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "sender_id")
    private Member sender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "courier_id")
    private Member courier;

    public static ChatRoom create(String name) {
        ChatRoom room = new ChatRoom();
        room.roomName = name;
        return room;
    }

    @Builder
    public ChatRoom(Long roomId, String roomName, Member sender, Member courier) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.sender = sender;
        this.courier = courier;
    }
}
