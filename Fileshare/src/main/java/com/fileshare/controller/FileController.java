package com.fileshare.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.fileshare.dto.FileRegisterRequest;
import com.fileshare.entity.FileMeta;
import com.fileshare.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	private final FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/register")
	public String register(@RequestBody FileRegisterRequest request) {
		return fileService.registerFile(request);
	}

	@GetMapping("/{roomKey}")
	public List<FileMeta> list(@PathVariable String roomKey) {
		return fileService.getFiles(roomKey);
	}
}
