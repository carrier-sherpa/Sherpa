package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.ChatMessage;
import com.sherpa.carrier_sherpa.domain.entity.ChatRoom;
import com.sherpa.carrier_sherpa.domain.repository.ChatMessageRepository;
import com.sherpa.carrier_sherpa.domain.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public void saveChatMsg(ChatMessage msg) {
        ChatRoom chatRoom = chatRoomRepository.findById(msg.getRoomId()).orElse(null);
        msg.setChatRoom(chatRoom);
        chatMessageRepository.save(msg);
    }
}
