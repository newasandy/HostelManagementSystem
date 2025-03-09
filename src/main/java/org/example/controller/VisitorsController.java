package org.example.controller;

import org.example.model.StatusMessageModel;
import org.example.model.Users;
import org.example.model.Visitors;
import org.example.service.VisitorService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class VisitorsController {

    private final VisitorService visitorService = new VisitorService();
    private UsersController usersController = new UsersController();

    public void getUserVisitedBy(Users users){
        List<Visitors> visitors = visitorService.userVisitedBy(users.getId());
        for (Visitors visitor : visitors){
            System.out.println(visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
        }
    }

    public void viewAllVisitor(){
        List<Visitors> visitors = visitorService.getAllVisitor();
        int sn = 1;
        for(Visitors visitor : visitors){
            System.out.println(sn +"\t\t"+visitor.getStudentId().getFullName()+"\t\t\t"+visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
            sn++;
        }
    }
    public void viewAllNotExitVisitor(){
        List<Visitors> visitors = visitorService.getAllNotExitVisitor();
        int sn = 1;
        for(Visitors visitor : visitors){
            System.out.println(sn +"\t\t"+visitor.getStudentId().getFullName()+"\t\t\t"+visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
            sn++;
        }
    }

    public Visitors getNotExitVisitorByRow(int rowNumber){
        List<Visitors> notExitVisitors = visitorService.getAllNotExitVisitor();
        if (rowNumber <1 || rowNumber > notExitVisitors.size()){
            System.out.println("Invalid Visitor");
            return null;
        }
        return notExitVisitors.get(rowNumber-1);
    }



    public StatusMessageModel addVisitor(int rowNumber, String visitorName, String relation, String reason){
        Visitors visitor = new Visitors();
        Users forUser = usersController.getOnlyStudentByRow(rowNumber);
        Date getEntryDate = new Date();
        Timestamp entryDateTime = new Timestamp(getEntryDate.getTime());
        visitor.setStudentId(forUser);
        visitor.setFullName(visitorName);
        visitor.setRelation(relation);
        visitor.setReason(reason);
        visitor.setEntryDatetime(entryDateTime);
        return visitorService.addVisitorService(visitor);

    }

    public StatusMessageModel updateVisitor(int rowNumber){
        Visitors exitVisitor = getNotExitVisitorByRow(rowNumber);
        Date date = new Date();
        Timestamp exitDate = new Timestamp(date.getTime());
        exitVisitor.setExitDatetime(exitDate);
        return visitorService.exitVisitorUpdate(exitVisitor);
    }
}
