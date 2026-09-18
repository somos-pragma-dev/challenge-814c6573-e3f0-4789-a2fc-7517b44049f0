package com.fintech.loansapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un préstamo en el sistema.
 * Mapea la tabla "loans" de la base de datos H2.
 * Los atributos incluyen: monto, tasa de interés, duración, estado y cliente.
 */
@Entity
@Table(name = "loans")
public class Loan {

    /**
     * Enumeración de estados posibles de un préstamo.
     */
    public enum LoanStatus {
        PENDING("Pendiente"),
        APPROVED("Aprobado"),
        REJECTED("Rechazado"),
        ACTIVE("Activo"),
        PAID("Pagado"),
        DEFAULTED("Incumplido");

        private final String descripcion;

        LoanStatus(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
    @DecimalMax(value = "100.0", message = "La tasa de interés no puede exceder el 100%")
    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima es 1 mes")
    @Max(value = 360, message = "La duración máxima es 360 meses")
    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del préstamo es obligatorio")
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status = LoanStatus.PENDING;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Loan() {
    }

    /**
     * Constructor con parámetros para crear un préstamo.
     */
    public Loan(BigDecimal amount, BigDecimal interestRate, Integer durationMonths, Long clientId) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.clientId = clientId;
        this.status = LoanStatus.PENDING;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Calcula el monto total a pagar con intereses.
     * @return monto total con intereses
     */
    public BigDecimal calculateTotalAmount() {
        if (amount == null || interestRate == null) {
            return amount;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual aproximada.
     * @return cuota mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total == null || durationMonths == null || durationMonths == 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Aprueba el préstamo estableciendo la fecha de inicio.
     */
    public void approve() {
        this.status = LoanStatus.APPROVED;
        this.startDate = LocalDate.now();
        if (this.durationMonths != null) {
            this.endDate = this.startDate.plusMonths(this.durationMonths);
        }
    }

    /**
     * Rechaza el préstamo.
     */
    public void reject() {
        this.status = LoanStatus.REJECTED;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", durationMonths=" + durationMonths +
                ", status=" + status +
                ", clientId=" + clientId +
                '}';
    }
}