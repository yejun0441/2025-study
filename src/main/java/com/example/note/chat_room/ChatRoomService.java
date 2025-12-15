package com.example.note.chat_room;

import com.example.note.account.Account;
import com.example.note.account.AccountRepository;
import com.example.note.chat_room.dto.CreateRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<?> createChatRoom(CreateRoomDto createRoomDto){
        Account account = accountRepository.findByAccountId(createRoomDto.getAccountId());

        if (account == null){
            System.out.println(createRoomDto.getAccountId());

            return ResponseEntity.status(401).header("Content-Type","application/json").body("존재하지 않는 계정입니다.");
        } // 필요없음 그냥 예외처리
        ChatRoom chatRoom = new ChatRoom();

        chatRoom.setAccount(account);
        chatRoom.setRoomName(createRoomDto.getRoomName());

        chatRoomRepository.save(chatRoom);
        return ResponseEntity.ok("채팅방 생성 완료");
    }


}
