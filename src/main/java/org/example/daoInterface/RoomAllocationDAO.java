package org.example.daoInterface;

import org.example.model.RoomAllocation;
import org.example.model.Rooms;
import org.example.model.Users;

import java.sql.Timestamp;

public interface RoomAllocationDAO extends BaseDAO<RoomAllocation, Long> {
    Long getRoomOccupancy(Rooms roomId);
    boolean unallocateStudent(Users studentId, Rooms roomId, Timestamp unallocationDate);
}
