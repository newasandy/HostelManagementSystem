package org.example.daoInterface;

import org.example.model.RoomAllocationModel;

public interface RoomAloocationDAO extends BaseDAO<RoomAllocationModel, Long> {
    Long getRoomOccupancy(Long roomId);
}
