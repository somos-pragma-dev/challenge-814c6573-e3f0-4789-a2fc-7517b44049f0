package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para representar un préstamo.
 * Utiliza la sintaxis de record para inmutabilidad.
 */
public record LoanResponse(
    Long id,
    BigDecimal amount,
    BigDecimal interestRate,
    Integer durationMonths,
    Loan.LoanStatus status,
    Long clientId,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    /**
     * Crea un LoanResponse a partir de una entidad Loan.
     */
    public static LoanResponse fromEntity(Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getStatus(),
            loan.getClientId(),
            loan.getDescription(),
            loan.getStartDate(),
            loan.getEndDate(),
            loan.getCreatedAt(),
            loan.getUpdatedAt()
        );
    }

    /**
     * Convierte este DTO en una entidad Loan (sin ID para nuevos préstamos).
     */
    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        loan.setStatus(this.status);
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStartDate(this.startDate);
        loan.setEndDate(this.endDate);
        return loan;
    }
}