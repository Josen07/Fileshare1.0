package com.fileshare.dto;

public class FileRegisterRequest {

	private String roomKey;
	private String sender;
	private String fileName;
	private long fileSize;
	private String fileType;

	public String getRoomKey() {
		return roomKey;
	}

	public String getSender() {
		return sender;
	}

	public String getFileName() {
		return fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getFileType() {
		return fileType;
	}
}
