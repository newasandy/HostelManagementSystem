package org.example.service;

import org.example.daoImplementation.VisitorsDAOImp;
import org.example.daoInterface.VisitorsDAO;
import org.example.model.StatusMessageModel;
import org.example.model.Visitors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class VisitorService {

    private final VisitorsDAO visitorsDAO = new VisitorsDAOImp();
    private StatusMessageModel statusMessageModel = new StatusMessageModel();

    public List<Visitors> userVisitedBy(Long userId){
        List<Visitors> visitors = visitorsDAO.getUserVisitedBy(userId);
        return visitors;
    }


    public void getAllVisitor(){
        List<Visitors> visitors = visitorsDAO.getAll();
        int rowNumber = 1;
        for(Visitors visitor : visitors){
            System.out.println(rowNumber +"\t\t"+visitor.getStudentId().getFullName()+"\t\t\t"+visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
            rowNumber++;
        }
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

    public StatusMessageModel exitVisitorUpdate(int rowNumber){
        List<Visitors> visitors = visitorsDAO.getAll();
        if (rowNumber < 0 || rowNumber > visitors.size()){
            System.out.println("Invalid Row Number");
        }
        Date date = new Date();
        Timestamp exitDate = new Timestamp(date.getTime());
        Visitors visitor = visitors.get(rowNumber-1);
        visitor.setExitDatetime(exitDate);

        if (visitorsDAO.update(visitor)){
            statusMessageModel.setStatus(true);
            statusMessageModel.setMessage("Visitor Update Successfully");
        }else {
            statusMessageModel.setStatus(false);
            statusMessageModel.setMessage("!! Can't Update Visitor");
        }
        return statusMessageModel;
    }
}
