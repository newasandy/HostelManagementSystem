package org.example.daoInterface;

import org.example.model.Rooms;

import java.sql.Timestamp;
import java.util.List;

public interface RoomDAO extends BaseDAO<Rooms, Long>{
    Rooms findByRoomNumber(int roomNumber);
    List<Rooms> getAvailableRoom();

}
