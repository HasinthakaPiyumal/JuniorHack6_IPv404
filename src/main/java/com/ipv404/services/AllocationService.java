package com.ipv404.services;

import com.ipv404.exceptions.NotFoundException;
import com.ipv404.models.Allocation;
import com.ipv404.models.Hostel;
import com.ipv404.repositories.AllocationRepository;

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
        return hostel;
    }
}
