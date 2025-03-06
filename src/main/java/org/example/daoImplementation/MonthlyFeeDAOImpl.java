package org.example.daoImplementation;

import org.example.daoInterface.MonthlyFeeDAO;
import org.example.model.MonthlyFee;

public class MonthlyFeeDAOImpl extends BaseDAOImp<MonthlyFee,Long> implements MonthlyFeeDAO {

    public MonthlyFeeDAOImpl(){
        super(MonthlyFee.class);
    }
}
