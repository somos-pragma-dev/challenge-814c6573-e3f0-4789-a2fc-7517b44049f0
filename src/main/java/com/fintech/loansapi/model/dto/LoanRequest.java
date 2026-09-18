package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;

import java.math.BigDecimal;

/**
 * DTO de solicitud para crear o actualizar un préstamo.
 * Utiliza la sintaxis de record para inmutabilidad.
 */
public record LoanRequest(
    BigDecimal amount,
    BigDecimal interestRate,
    Integer durationMonths,
    Long clientId,
    String description
) {
    /**
     * Convierte este DTO en una entidad Loan.
     */
    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStatus(Loan.LoanStatus.PENDING);
        return loan;
    }

    /**
     * Actualiza una entidad Loan existente con los valores de este DTO.
     */
    public void updateEntity(Loan loan) {
        if (this.amount != null) {
            loan.setAmount(this.amount);
        }
        if (this.interestRate != null) {
            loan.setInterestRate(this.interestRate);
        }
        if (this.durationMonths != null) {
            loan.setDurationMonths(this.durationMonths);
        }
        if (this.clientId != null) {
            loan.setClientId(this.clientId);
        }
        if (this.description != null) {
            loan.setDescription(this.description);
        }
    }

    /**
     * Crea un LoanRequest a partir de una entidad Loan.
     */
    public static LoanRequest fromEntity(Loan loan) {
        return new LoanRequest(
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getClientId(),
            loan.getDescription()
        );
    }

    /**
     * Valida que los campos del request sean correctos.
     */
    public boolean isValid() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && interestRate != null && interestRate.compareTo(BigDecimal.ZERO) >= 0
            && durationMonths != null && durationMonths > 0
            && clientId != null;
    }

    /**
     * Calcula el monto total del préstamo.
     */
    public BigDecimal calculateTotalAmount() {
        if (amount == null || interestRate == null) {
            return amount;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual.
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total == null || durationMonths == null || durationMonths == 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }
}