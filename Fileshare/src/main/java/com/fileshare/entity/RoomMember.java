package com.fileshare.entity;

public class RoomMember {

	private String username;
	private boolean canSend;
	
	
	public RoomMember(String username, boolean canSend) {
		super();
		this.username = username;
		this.canSend = canSend;
	}
	
	public String getUsername() {
        return username;
    }

    public boolean isCanSend() {
        return canSend;
    }

    public void setCanSend(boolean canSend) {
        this.canSend = canSend;
    }
	
}
