package com.fintech.loansapi.exception;

import com.fintech.loansapi.model.entity.Loan;
import java.time.LocalDateTime;
import java.util.Map;

public class LoanNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final Long loanId;
    private final String requestedOperation;
    private final LocalDateTime timestamp;
    private final Map<String, Object> context;
    
    public LoanNotFoundException(Long loanId) {
        super(buildMessage(loanId, "consulta"));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        this.loanId = loanId;
        this.requestedOperation = "consulta";
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id"
        );
    }
    
    public LoanNotFoundException(Long loanId, String operation) {
        super(buildMessage(loanId, operation));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException("La operación no puede ser nula o vacía");
        }
        this.loanId = loanId;
        this.requestedOperation = operation;
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id",
            "operation", operation
        );
    }
    
    private static String buildMessage(Long loanId, String operation) {
        return String.format("Préstamo no encontrado con ID: %d durante operación de %s", loanId, operation);
    }
    
    public Long getLoanId() {
        return loanId;
    }
    
    public String getRequestedOperation() {
        return requestedOperation;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public Map<String, Object> getContext() {
        return context;
    }
    
    public String getDebugInfo() {
        return String.format("LoanNotFoundException{id=%d, operation='%s', timestamp=%s}", 
            loanId, requestedOperation, timestamp);
    }
    
    @Override
    public String toString() {
        return "LoanNotFoundException{" +
                "loanId=" + loanId +
                ", requestedOperation='" + requestedOperation + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}