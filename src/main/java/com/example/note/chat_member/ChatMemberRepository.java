package com.example.note.chat_member;

import com.example.note.account.Account;
import com.example.note.chat_room.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findAllByChatRoom(ChatRoom chatRoom);

    ChatMember findRoomIdByAccountId(Account account);

    ChatMember findAllByAccountId(Account account);

    ChatMember findByAccountId(Account accountId);
}
