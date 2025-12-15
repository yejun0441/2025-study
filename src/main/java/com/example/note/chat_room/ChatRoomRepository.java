package com.example.note.chat_room;


import com.example.note.account.Account;
import com.example.note.chat_member.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByRoomName(String roomName);

    ChatRoom findRoomIdByRoomName(String roomName);

    List<ChatRoom> findAllByAccount(Account account);

    ChatRoom findRoomIdByAccount(Account account);

    List<ChatRoom> findAllByRoomId(ChatMember chatMember);

    @Query("""
SELECT distinct cr FROM ChatMember AS cm JOIN
ChatRoom AS cr  ON cm.chatRoom.roomId = cr.roomId
JOIN Account AS a ON cm.accountId.accountId = :accountId
""")
    List<ChatRoom> findMyRooms(Long accountId);
}
