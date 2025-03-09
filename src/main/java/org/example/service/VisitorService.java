package org.example.service;

import org.example.daoImplementation.VisitorsDAOImp;
import org.example.daoInterface.VisitorsDAO;
import org.example.model.StatusMessageModel;
import org.example.model.Visitors;

import java.util.List;

public class VisitorService {

    private final VisitorsDAO visitorsDAO;
    private StatusMessageModel statusMessageModel = new StatusMessageModel();

    public VisitorService(VisitorsDAO visitorsDAO){
        this.visitorsDAO=visitorsDAO;
    }

    public List<Visitors> userVisitedBy(Long userId){
        List<Visitors> visitors = visitorsDAO.getUserVisitedBy(userId);
        return visitors;
    }


    public List<Visitors> getAllVisitor(){
        return visitorsDAO.getAll();
    }

    public List<Visitors> getAllNotExitVisitor(){
        return visitorsDAO.getAllNotExitVistior();
    }

    public StatusMessageModel addVisitorService(Visitors visitor){
        if (visitorsDAO.add(visitor)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Add Visitor Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Not Added visitor");
        }
        return statusMessageModel;
    }

    public StatusMessageModel exitVisitorUpdate(Visitors exitVisitor){
        if (visitorsDAO.update(exitVisitor)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Visitor Update Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Can't Update Visitor");
        }
        return statusMessageModel;
    }
}
