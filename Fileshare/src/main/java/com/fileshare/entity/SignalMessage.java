package com.fileshare.entity;

public class SignalMessage {

	private String from;
	private String to;
	private String roomKey;
	private String type; // OFFER, ANSWER, ICE
	private String payload; // SDP or ICE JSON string

	public SignalMessage(String from, String to, String roomKey, String type, String payload) {
		this.from = from;
		this.to = to;
		this.roomKey = roomKey;
		this.type = type;
		this.payload = payload;
	}

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
