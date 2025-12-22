package com.fileshare.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fileshare.dto.JoinRoomRequest;
import com.fileshare.entity.Room;
import com.fileshare.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping("/create")
	public Room createRoom(@RequestParam String owner) {
		return roomService.createRoom(owner);
	}

	@PostMapping("/join")
	public String joinRoom(@RequestBody JoinRoomRequest request) {
		return roomService.joinRoom(request.getRoomKey(), request.getUsername());
	}
}
