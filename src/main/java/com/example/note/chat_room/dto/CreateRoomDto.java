package com.example.note.chat_room.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateRoomDto {

    private Long accountId;

    private String roomName;
}
