package org.example.service;

import org.example.daoImplementation.VisitorsDAOImp;
import org.example.daoInterface.VisitorsDAO;
import org.example.model.Visitors;

import java.util.List;

public class VisitorService {
    private final VisitorsDAO visitorsDAO = new VisitorsDAOImp();
    public List<Visitors> userVisitedBy(Long userId){
        List<Visitors> visitors = visitorsDAO.getUserVisitedBy(userId);
        return visitors;
    }
}
