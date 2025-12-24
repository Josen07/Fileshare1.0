package com.fileshare.scheduler;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fileshare.entity.Room;
import com.fileshare.store.InMemoryStore;

@Component
public class RoomExpiryScheduler {

	private final InMemoryStore store;

	public RoomExpiryScheduler(InMemoryStore store) {
		
		this.store = store;
	}
	
	public void removeExpiredRooms() {
		
		Iterator<Map.Entry<String, Room>> iterator =
                store.rooms.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, Room> entry = iterator.next();
            Room room = entry.getValue();

            if (LocalDateTime.now().isAfter(room.getExpiryTime())) {
                iterator.remove();
                System.out.println("Room expired & removed: " + room.getRoomKey());
            }
        }
	}
}
