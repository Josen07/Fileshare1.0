package com.fileshare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fileshare.dto.FileRegisterRequest;
import com.fileshare.entity.FileMeta;
import com.fileshare.entity.Room;
import com.fileshare.entity.RoomMember;
import com.fileshare.store.InMemoryStore;

@Service
public class FileService {

	private final InMemoryStore store;

	public FileService(InMemoryStore store) {
		this.store = store;
	}

	public String registerFile(FileRegisterRequest req) {

		Room room = store.rooms.get(req.getRoomKey());
		if (room == null)
			return "ROOM_NOT_FOUND";

		RoomMember member = room.getMembers().get(req.getSender());
		if (member == null)
			return "NOT_IN_ROOM";

		if (!member.isCanSend())
			return "SEND_PERMISSION_DENIED";

		FileMeta meta = new FileMeta(UUID.randomUUID().toString(), req.getFileName(), req.getFileSize(),
				req.getFileType(), req.getSender(), req.getRoomKey());

		store.roomFiles.computeIfAbsent(req.getRoomKey(), k -> new ArrayList<>()).add(meta);

		return "FILE_REGISTERED";
	}

	public List<FileMeta> getFiles(String roomKey) {
		return store.roomFiles.getOrDefault(roomKey, new ArrayList<>());
	}
}
