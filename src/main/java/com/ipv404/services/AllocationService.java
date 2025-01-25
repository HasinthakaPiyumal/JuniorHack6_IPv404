package com.ipv404.services;

import com.ipv404.exceptions.NotFoundException;
import com.ipv404.models.Allocation;
import com.ipv404.models.Hostel;
import com.ipv404.models.Student;
import com.ipv404.repositories.AllocationRepository;

import java.util.List;

public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(AllocationRepository allocationRepository){
        this.allocationRepository = allocationRepository;
    }

    public Allocation createAllocation(Allocation allocation){
        return allocationRepository.save(allocation);
    }

    public Allocation getAllocationById(Long id) throws NotFoundException {
        Allocation allocation = allocationRepository.findById(id);
        if (allocation == null) {
            throw new NotFoundException("Hostel not found with id: " + id);
        }
        return allocation;
    }

    public void deleteAllocation(Long id) throws NotFoundException {
        if (!allocationRepository.delete(id)) {
            throw new NotFoundException("Allocation not found with id: " + id);
        }
    }

    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public void updateAllocation(Allocation allocation) {
        allocationRepository.update(allocation);
    }
}
