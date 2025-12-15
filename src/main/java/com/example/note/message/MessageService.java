package com.example.note.message;

import com.example.note.chat_room.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final ChatRoomRepository chatRoomRepository;
//
//    public List<Message> findAllMessage (Long roomId) {
//        return chatRoomRepository.findAllByRoomId();
//
//    }
}
