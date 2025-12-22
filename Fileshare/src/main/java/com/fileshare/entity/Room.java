package com.fileshare.entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Room {

	private String roomKey;
	private String owner;
	private LocalDateTime expiryTime;
	private Map<String, RoomMember> members = new HashMap<>();
	
	
	public Room(String roomKey, String owner, LocalDateTime expiryTime) {
		super();
		this.roomKey = roomKey;
		this.owner = owner;
		this.expiryTime = expiryTime;
	}
	
	public String getRoomKey() {
        return roomKey;
    }

    public String getOwner() {
        return owner;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public Map<String, RoomMember> getMembers() {
        return members;
    }
	
}
