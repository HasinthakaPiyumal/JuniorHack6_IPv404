package com.ipv404.services;

import com.ipv404.exceptions.NotFoundException;
import com.ipv404.models.Room;
import com.ipv404.repositories.RoomRepository;

import java.util.List;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room){
        return roomRepository.save(room);
    }

    public Room getRoomById(Long id) throws NotFoundException {
        Room room = roomRepository.findById(id);
        if (room == null) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        return room;
    }

    public void deleteHostel(Long id) throws NotFoundException {
        if (!roomRepository.delete(id)) {
            throw new NotFoundException("Hostel not found with id: " + id);
        }
    }

    public List<Room> getAllHostels() {
        return roomRepository.findAll();
    }

}
