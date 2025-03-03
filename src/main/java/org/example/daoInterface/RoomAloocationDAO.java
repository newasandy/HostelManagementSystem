package org.example.daoInterface;

import org.example.model.RoomAllocationModel;

import java.sql.Timestamp;

public interface RoomAloocationDAO extends BaseDAO<RoomAllocationModel, Long> {
    Long getRoomOccupancy(Long roomId);
    boolean unallocateStudent(Long studentId, Long roomId, Timestamp unallocationDate);
}
