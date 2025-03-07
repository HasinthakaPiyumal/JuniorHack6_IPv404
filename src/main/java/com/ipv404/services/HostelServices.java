package com.ipv404.services;

import com.ipv404.exceptions.NotFoundException;
import com.ipv404.models.Hostel;
import com.ipv404.models.Student;
import com.ipv404.repositories.HostelRepository;

import java.util.List;

public class HostelServices {

    private final HostelRepository hostelRepository;

    public HostelServices() {
        this.hostelRepository = new HostelRepository();
    }

    public Hostel createHostel(Hostel hostel){
        return hostelRepository.save(hostel);
    }

    public Hostel getHostelById(Long id) throws NotFoundException {
        Hostel hostel = hostelRepository.findById(id);
        if (hostel == null) {
            throw new NotFoundException("Hostel not found with id: " + id);
        }
        return hostel;
    }

    public void deleteHostel(String id) throws NotFoundException {
        if (!hostelRepository.delete(id)) {
            throw new NotFoundException("Hostel not found with id: " + id);
        }
    }

    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }

    public boolean updateHostel(Hostel hostel) {
        return hostelRepository.update(hostel);
    }
}
