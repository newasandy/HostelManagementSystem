package org.example.controller;

import org.example.model.RoomAllocation;
import org.example.model.Users;
import org.example.model.Visitors;
import org.example.service.RoomAllocationService;

import java.util.List;

public class RoomAllocationController {
    private final RoomAllocationService roomAllocationService = new RoomAllocationService();

    public void getUserAllocatedDetailsController(Users users){
        List<RoomAllocation> allocatedDetails = roomAllocationService.getAllocatedDetails(users.getId());
        for (RoomAllocation allocation : allocatedDetails){
            System.out.println(allocation.getRoomId().getRoomNumber() + " \t\t"+allocation.getAllocationDate() +"\t\t\t" + allocation.getUnallocationDate());
        }
    }

    public void getUserVisitedByController(Users users){
        List<Visitors> visitors ;
    }
}
