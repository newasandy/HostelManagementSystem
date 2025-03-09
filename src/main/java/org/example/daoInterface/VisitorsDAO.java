package org.example.daoInterface;

import org.example.model.Visitors;

import java.util.List;

public interface VisitorsDAO extends BaseDAO<Visitors, Long> {

    List<Visitors> getUserVisitedBy(Long userId);
    List<Visitors> getAllNotExitVistior();
}
