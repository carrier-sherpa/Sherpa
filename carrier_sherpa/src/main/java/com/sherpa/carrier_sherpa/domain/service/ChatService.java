package com.sherpa.carrier_sherpa.domain.service;

import com.sherpa.carrier_sherpa.domain.entity.ChatRoom;
import com.sherpa.carrier_sherpa.domain.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<Long, ChatRoom> chatRooms;

    private final ChatRoomRepository chatRoomRepository;

    @PostConstruct

    //의존관계 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 불러오기
    public List<ChatRoom> findAllRoom() {
        //채팅방 최근 생성 순으로 반환
        List<ChatRoom> result = new ArrayList<>(chatRooms.values());
        Collections.reverse(result);

        return result;
    }

    //채팅방 하나 불러오기
    public ChatRoom findById(String roomId) {
        return chatRooms.get(roomId);
    }

    //채팅방 생성
    public ChatRoom createRoom(String name) {
        ChatRoom chatRoom = ChatRoom.builder()
                                    .roomName(name)
                                    .build();
        chatRoomRepository.save(chatRoom);
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
