package org.example.daoImplementation;

import org.example.daoInterface.VisitorsDAO;
import org.example.model.Visitors;

public class VisitorsDAOImp extends BaseDAOImp<Visitors , Long> implements VisitorsDAO {

    public VisitorsDAOImp(){
        super(Visitors.class);
    }

}
