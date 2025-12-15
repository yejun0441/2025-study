package com.example.note.chat_room;

import com.example.note.chat_room.dto.CreateRoomDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ChatRoom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatRoomRepository chatRoomRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomDto createRoomDto) {
        return chatRoomService.createChatRoom(createRoomDto);
    }
    @GetMapping("/loadRoom")
    public List<ChatRoom> loadRoom(@RequestParam Long accountId) {
        return chatRoomRepository.findMyRooms(accountId);
    }

}
