package com.fileshare.entity;

import java.time.LocalDateTime;

public class FileMeta {

	private String fileId;
	private String fileName;
	private long fileSize;
	private String fileType;
	private String sender;
	private String roomKey;
	private LocalDateTime uploadedAt;

	public FileMeta(String fileId, String fileName, long fileSize, String fileType, String sender, String roomKey) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.sender = sender;
		this.roomKey = roomKey;
		this.uploadedAt = LocalDateTime.now();
	}

	public String getFileId() {
		return fileId;
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

	public String getSender() {
		return sender;
	}

	public String getRoomKey() {
		return roomKey;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}
}
