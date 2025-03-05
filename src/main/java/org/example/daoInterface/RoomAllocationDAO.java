package org.example.daoInterface;

import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.Users;

import java.sql.Timestamp;
import java.util.List;

public interface RoomAllocationDAO extends BaseDAO<RoomAllocation, Long> {
    Long getRoomOccupancy(Rooms roomId);
    boolean unallocateStudent(Long studentId, Long roomId, Timestamp unallocationDate);
    List<RoomAllocation> getUserAllocated(Long userId);
    boolean disableRoomUnallocatedStudent(Long roomId, Timestamp unallocationDate);
}
