package org.example.daoInterface;

import org.example.model.RoomAllocation;

import java.sql.Timestamp;

public interface RoomAloocationDAO extends BaseDAO<RoomAllocation, Long> {
    Long getRoomOccupancy(Long roomId);
    boolean unallocateStudent(Long studentId, Long roomId, Timestamp unallocationDate);
}
