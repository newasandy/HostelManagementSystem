package org.example.service;

import org.example.daoImplementation.RoomAllocationDAOImp;
import org.example.daoInterface.RoomAllocationDAO;
import org.example.model.RoomAllocation;

import java.util.List;

public class RoomAllocationService {
    private RoomAllocationDAO roomAllocationDAO = new RoomAllocationDAOImp();
    public List<RoomAllocation> getAllocatedDetails(Long userId){
        List<RoomAllocation> allocatedDetails = roomAllocationDAO.getUserAllocated(userId);
        return allocatedDetails;
    }
}
