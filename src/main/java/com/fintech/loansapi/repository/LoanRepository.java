package com.fintech.loansapi.repository;

import com.fintech.loansapi.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Loan.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    Optional<Loan> findById(Long id);
    
    List<Loan> findAllByOrderByCreatedAtDesc();
    
    List<Loan> findByStatus(Loan.LoanStatus estado);
    
    List<Loan> findByAmountBetween(BigDecimal montoMin, BigDecimal montoMax);
    
    List<Loan> findByClientId(Long clientId);
    
    @Query("SELECT l FROM Loan l WHERE l.status = 'APPROVED' AND l.amount >= :montoMin")
    List<Loan> findApprovedLoansWithMinAmount(@Param("montoMin") BigDecimal montoMin);
    
    @Query(value = "SELECT COUNT(*) FROM loans WHERE status = :status", nativeQuery = true)
    Long countByStatus(@Param("status") String status);
    
    List<Loan> findByCreatedAtAfter(LocalDate fecha);
    
    boolean existsById(Long id);
    
    void deleteById(Long id);
    
    /**
     * Guarda un préstamo en la base de datos.
     * @param loan el préstamo a guardar
     * @return el préstamo guardado con su ID generado
     */
    Loan save(Loan loan);
}