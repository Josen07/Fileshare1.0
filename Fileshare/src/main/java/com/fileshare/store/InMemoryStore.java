package com.fileshare.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.fileshare.entity.Room;

// for temporary database purpose...

@Component
public class InMemoryStore {

	public Map<String, Room> rooms = new ConcurrentHashMap<>();
}
