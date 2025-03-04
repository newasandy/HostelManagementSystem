package org.example.daoInterface;

import org.example.model.Rooms;

import java.sql.Timestamp;

public interface RoomDAO extends BaseDAO<Rooms, Long>{
    boolean deleteOnlyRoom(Long roomId, Timestamp unallocationDate);
}
