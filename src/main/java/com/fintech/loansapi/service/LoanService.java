package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request);
    LoanResponse updateLoan(Long id, LoanRequest request);
    void deleteLoan(Long id);
    LoanResponse getLoanById(Long id);
    List<LoanResponse> getAllLoans();
    List<LoanResponse> getLoansByStatus(LoanStatus status);
    List<LoanResponse> getLoansByAmountRange(BigDecimal minAmount, BigDecimal maxAmount);
    List<LoanResponse> getLoansByClientId(Long clientId);
    List<LoanResponse> getApprovedLoansWithMinAmount(BigDecimal minAmount);
    Long countLoansByStatus(LoanStatus status);
    List<LoanResponse> getLoansCreatedAfter(LocalDate fecha);
    LoanResponse approveLoan(Long id);
    LoanResponse rejectLoan(Long id);
}