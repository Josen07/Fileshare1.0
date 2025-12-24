package com.fileshare.dto;

public class SignalRequest {

	private String from;
	private String to;
	private String roomKey;
	private String type;
	private String payload;

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getRoomKey() {
		return roomKey;
	}

	public String getType() {
		return type;
	}

	public String getPayload() {
		return payload;
	}
}
