package com.fintech.loansapi.service;

import com.fintech.loansapi.exception.LoanNotFoundException;
import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import com.fintech.loansapi.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    
    private final LoanRepository loanRepository;
    
    public LoanServiceImpl(LoanRepository loanRepository) {
        if (loanRepository == null) {
            throw new IllegalArgumentException("LoanRepository no puede ser nulo");
        }
        this.loanRepository = loanRepository;
    }
    
    @Override
    public LoanResponse createLoan(LoanRequest request) {
        logger.info("Creando nuevo préstamo para cliente: {}", request.clientId());
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        if (!request.isValid()) {
            throw new IllegalArgumentException("Los datos del préstamo no son válidos: " + 
                    "monto debe ser positivo, tasa de interés debe ser positiva, duración debe ser mayor a 0");
        }
        
        Loan loan = request.toEntity();
        loan.setStatus(LoanStatus.PENDING);
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan savedLoan = loanRepository.save(loan);
        logger.info("Préstamo creado exitosamente con ID: {}", savedLoan.getId());
        
        return LoanResponse.fromEntity(savedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long id) {
        logger.debug("Buscando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Optional<Loan> loanOptional = loanRepository.findById(id);
        
        if (loanOptional.isEmpty()) {
            logger.warn("Préstamo no encontrado con ID: {}", id);
            throw new LoanNotFoundException(id, "consulta");
        }
        
        Loan loan = loanOptional.get();
        logger.debug("Préstamo encontrado: {}", loan.getId());
        
        return LoanResponse.fromEntity(loan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getAllLoans() {
        logger.debug("Obteniendo todos los préstamos");
        
        List<Loan> loans = loanRepository.findAllByOrderByCreatedAtDesc();
        
        logger.info("Se encontraron {} préstamos", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse updateLoan(Long id, LoanRequest request) {
        logger.info("Actualizando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para actualización con ID: {}", id);
                    return new LoanNotFoundException(id, "actualización");
                });
        
        request.updateEntity(existingLoan);
        existingLoan.setUpdatedAt(LocalDateTime.now());
        
        Loan updatedLoan = loanRepository.save(existingLoan);
        logger.info("Préstamo actualizado exitosamente: {}", updatedLoan.getId());
        
        return LoanResponse.fromEntity(updatedLoan);
    }
    
    @Override
    public void deleteLoan(Long id) {
        logger.info("Eliminando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (!loanRepository.existsById(id)) {
            logger.warn("Préstamo no encontrado para eliminación con ID: {}", id);
            throw new LoanNotFoundException(id, "eliminación");
        }
        
        loanRepository.deleteById(id);
        logger.info("Préstamo eliminado exitosamente: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByStatus(LoanStatus status) {
        logger.debug("Buscando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        List<Loan> loans = loanRepository.findByStatus(status);
        
        logger.info("Se encontraron {} préstamos con estado {}", loans.size(), status);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        logger.debug("Buscando préstamos entre {} y {}", minAmount, maxAmount);
        
        if (minAmount == null || maxAmount == null) {
            throw new IllegalArgumentException("Los montos mínimo y máximo no pueden ser nulos");
        }
        
        if (minAmount.compareTo(BigDecimal.ZERO) <= 0 || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Los montos deben ser valores positivos");
        }
        
        if (minAmount.compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("El monto mínimo no puede ser mayor que el máximo");
        }
        
        List<Loan> loans = loanRepository.findByAmountBetween(minAmount, maxAmount);
        
        logger.info("Se encontraron {} préstamos en el rango de monto", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByClientId(Long clientId) {
        logger.debug("Buscando préstamos para cliente: {}", clientId);
        
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un valor positivo");
        }
        
        List<Loan> loans = loanRepository.findByClientId(clientId);
        
        logger.info("Se encontraron {} préstamos para el cliente {}", loans.size(), clientId);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse approveLoan(Long id) {
        logger.info("Aprobando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para aprobación con ID: {}", id);
                    return new LoanNotFoundException(id, "aprobación");
                });
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.approve();
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(loan.getDurationMonths()));
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan approvedLoan = loanRepository.save(loan);
        logger.info("Préstamo aprobado exitosamente: {}", approvedLoan.getId());
        
        return LoanResponse.fromEntity(approvedLoan);
    }
    
    @Override
    public LoanResponse rejectLoan(Long id) {
        logger.info("Rechazando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para rechazo con ID: {}", id);
                    return new LoanNotFoundException(id, "rechazo");
                });
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.reject();
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan rejectedLoan = loanRepository.save(loan);
        logger.info("Préstamo rechazado exitosamente: {}", rejectedLoan.getId());
        
        return LoanResponse.fromEntity(rejectedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countLoansByStatus(LoanStatus status) {
        logger.debug("Contando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        Long count = loanRepository.countByStatus(status.name());
        logger.info("Total de préstamos con estado {}: {}", status, count);
        
        return count;
    }
}