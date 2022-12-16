package com.sherpa.carrier_sherpa.Controller;

import com.sherpa.carrier_sherpa.domain.entity.ChatMessage;
import com.sherpa.carrier_sherpa.domain.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
//            ChatMessage.builder()
//                            .m
            message.setMessage(message.getSender()+"님이 입장하였습니다.");
        }
        chatMessageService.saveChatMsg(message);
        sendingOperations.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}
