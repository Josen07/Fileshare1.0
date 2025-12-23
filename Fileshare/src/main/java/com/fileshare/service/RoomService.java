package com.fileshare.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fileshare.dto.FileSendPermissionRequest;
import com.fileshare.entity.Room;
import com.fileshare.entity.RoomMember;
import com.fileshare.store.InMemoryStore;

@Service
public class RoomService {

	private final InMemoryStore store;

	public RoomService(InMemoryStore store) {

		this.store = store;
	}

	public Room createRoom(String owner) {

		String roomKey = UUID.randomUUID().toString().substring(0, 6);

		Room room = new Room(roomKey, owner, LocalDateTime.now().plusMinutes(60));

		room.getMembers().put(owner, new RoomMember(owner, true));

		store.rooms.put(roomKey, room);

		return room;
	}

	public String joinRoom(String roomKey, String username) {

		Room room = store.rooms.get(roomKey);

		if (room == null) {
			return "ROOM_NOT_FOUND";
		}

		if (room.getExpiryTime().isBefore(LocalDateTime.now())) {
			store.rooms.remove(roomKey);
			return "ROOM_EXPIRED";
		}

		if (room.getMembers().containsKey(username)) {
			return "ALREADY_JOINED";
		}

		room.getMembers().put(username, new RoomMember(username, false));

		return "JOINED_SUCCESSFULLY";

	}

	public String leaveRoom(String roomKey, String username) {

		Room room = store.rooms.get(roomKey);

		if (room == null) {
			return "ROOM_NOT_FOUND";
		}

		if (!room.getMembers().containsKey(username)) {
			return "NOT_A_MEMBER";
		}

		// Remove user
		room.getMembers().remove(username);

		// If room is empty → delete room
		if (room.getMembers().isEmpty()) {
			store.rooms.remove(roomKey);
			return "ROOM_DELETED";
		}

		// If owner left → transfer ownership
		if (room.getOwner().equals(username)) {
			String newOwner = room.getMembers().keySet().iterator().next();
			room.getMembers().get(newOwner).setCanSend(true);
			room = new Room(roomKey, newOwner, room.getExpiryTime());
			store.rooms.put(roomKey, room);
			return "OWNERSHIP_TRANSFERRED_TO_" + newOwner;
		}

		return "LEFT_ROOM_SUCCESSFULLY";
	}

	public String allowSend(FileSendPermissionRequest req) {

		Room room = store.rooms.get(req.getRoomKey());

		if (room == null)
			return "ROOM_NOT_FOUND";

		if (!room.getOwner().equals(req.getOwner()))
			return "NOT_ROOM_OWNER";

		RoomMember member = room.getMembers().get(req.getTargetUser());

		if (member == null)
			return "USER_NOT_IN_ROOM";

		member.setCanSend(true);
		return "SEND_PERMISSION_GRANTED";
	}

	public String revokeSend(FileSendPermissionRequest req) {

		Room room = store.rooms.get(req.getRoomKey());

		if (room == null)
			return "ROOM_NOT_FOUND";

		if (!room.getOwner().equals(req.getOwner()))
			return "NOT_ROOM_OWNER";

		RoomMember member = room.getMembers().get(req.getTargetUser());

		if (member == null)
			return "USER_NOT_IN_ROOM";

		member.setCanSend(false);
		return "SEND_PERMISSION_REVOKED";
	}

}
