package org.example.controller;

import org.example.model.Users;
import org.example.model.Visitors;
import org.example.service.VisitorService;

import java.util.List;

public class VisitorsController {
    private final VisitorService visitorService = new VisitorService();
    public void userVisitedBy(Users users){
        List<Visitors> visitors = visitorService.userVisitedBy(users.getId());
        for (Visitors visitor : visitors){
            System.out.println(visitor.getFullName()+"\t\t\t"+visitor.getRelation()+"\t\t\t"+visitor.getReason()+"\t\t\t"+visitor.getEntryDatetime()+"\t\t\t"+visitor.getExitDatetime());
        }
    }
}
