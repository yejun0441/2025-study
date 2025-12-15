package com.example.note.chat_member;


import com.example.note.account.Account;
import com.example.note.account.AccountRepository;
import com.example.note.account.AccountService;
import com.example.note.chat_member.dto.AddMemberDto;
import com.example.note.chat_room.ChatRoom;
import com.example.note.chat_room.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMemberService {
    private final ChatMemberRepository chatMemberRepository;
    private final AccountRepository accountRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ResponseEntity<?> addMember(AddMemberDto addMemberDto) {
        Account account = accountRepository.findAccountIdByUserId(addMemberDto.getUserId());
        if (account == null) {
            return ResponseEntity.status(404).header("Content-Type","application/json").body("계정을 찾을 수 없음");
        }
        ChatRoom chatRoom = chatRoomRepository.findRoomIdByRoomName(addMemberDto.getRoomName());
        if (chatRoom == null) {
            return ResponseEntity.status(404).header("Content-Type", "application/json").body("채팅방을 찾을 수 없습니다");
        }
        ChatMember chatMember = new ChatMember();
        chatMember.setChatRoom(chatRoom);
        chatMember.setAccountId(account);
        chatMemberRepository.save(chatMember);
        return ResponseEntity.ok("추가 완료");
    }

    public List<ChatMember> findAllMembers(String roomName) {
        ChatRoom chatRoom = chatRoomRepository.findRoomIdByRoomName(roomName);
        return chatMemberRepository.findAllByChatRoom(chatRoom);
    }

    public List<ChatRoom> findMyRoom(Long accountId){
        Account account = accountRepository.findByAccountId(accountId);
        ChatMember chatmember = chatMemberRepository.findAllByAccountId(account);
        return chatRoomRepository.findAllByRoomId(chatmember);
    }
}
