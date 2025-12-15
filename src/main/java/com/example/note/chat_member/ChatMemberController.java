package com.example.note.chat_member;

import com.example.note.chat_member.dto.AddMemberDto;
import com.example.note.chat_room.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatMembers")
public class ChatMemberController {
    private final ChatMemberService chatMemberService;

    @PostMapping("/addMembers")
    public ResponseEntity<?> addMember(@RequestBody AddMemberDto addMemberDto) {
        return chatMemberService.addMember(addMemberDto);
    }

    @GetMapping("/findRoomMembers")
    public List<ChatMember> findRoomMembers(@RequestParam String roomName) {
        return chatMemberService.findAllMembers(roomName);
    }
    @GetMapping("/findMyRooms")
    public List<ChatRoom> roomMembers(@RequestParam Long accountId) {
        return chatMemberService.findMyRoom(accountId);
    }
}
