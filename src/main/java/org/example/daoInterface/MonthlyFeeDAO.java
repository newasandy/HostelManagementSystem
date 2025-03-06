package org.example.daoInterface;

import org.example.model.MonthlyFee;

import java.util.List;

public interface MonthlyFeeDAO extends BaseDAO<MonthlyFee,Long> {
    List<MonthlyFee> getUserFeeDetails(Long userId);
    List<MonthlyFee> getUserUnPaidFee(Long userId);
    List<MonthlyFee> getAllUserUnPaidFee();
}
