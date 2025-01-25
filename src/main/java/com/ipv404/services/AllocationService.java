package com.ipv404.services;

import java.util.List;

import com.ipv404.exceptions.NotFoundException;
import com.ipv404.models.Allocation;
import com.ipv404.models.Hostel;
import com.ipv404.repositories.AllocationRepository;

public class AllocationService {

    private final AllocationRepository allocationRepository;

    public AllocationService(){
        this.allocationRepository = new AllocationRepository();
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

    public List<Allocation> getAllAllocations() {
        return allocationRepository.findAll();
    }

    public boolean deleteAllocation(String id) {
        return allocationRepository.delete(id);
    }
}
