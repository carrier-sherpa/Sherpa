package com.sherpa.carrier_sherpa.domain.entity;

import com.sherpa.carrier_sherpa.domain.repository.ChatRoomRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "chat_message")
@NoArgsConstructor
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_message_id")
    private Long id;

    private MessageType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    private Long roomId;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String message;

    @Builder
    public ChatMessage(Long id, MessageType type, ChatRoom chatRoom, Long roomId, String sender, String message) {
        this.id = id;
        this.type = type;
        this.chatRoom = chatRoom;
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
    }
}
