package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanService")
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan loan;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(Loan.LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo para vehículo"
        );
    }

    @Test
    @DisplayName("getAllLoans - Debe retornar todos los préstamos ordenados por fecha")
    void testGetAllLoans_ReturnsAllLoans() {
        List<Loan> loans = Arrays.asList(loan);
        when(loanRepository.findAllByOrderByCreatedAtDesc()).thenReturn(loans);

        List<LoanResponse> result = loanService.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        verify(loanRepository, times(1)).findAllByOrderByCreatedAtDesc();
    }

    @Test
    @DisplayName("getLoanById - Debe retornar préstamo cuando existe")
    void testGetLoanById_WhenExists_ReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Optional<LoanResponse> result = loanService.getLoanById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        assertEquals(new BigDecimal("10000.00"), result.get().amount());
        verify(loanRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getLoanById - Debe retornar vacío cuando no existe")
    void testGetLoanById_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.getLoanById(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("createLoan - Debe crear y retornar el préstamo")
    void testCreateLoan_CreatesAndReturnsLoan() {
        Loan savedLoan = new Loan();
        savedLoan.setId(1L);
        savedLoan.setAmount(loanRequest.amount());
        savedLoan.setInterestRate(loanRequest.interestRate());
        savedLoan.setDurationMonths(loanRequest.durationMonths());
        savedLoan.setClientId(loanRequest.clientId());
        savedLoan.setDescription(loanRequest.description());
        savedLoan.setStatus(Loan.LoanStatus.PENDING);
        savedLoan.setCreatedAt(LocalDateTime.now());
        savedLoan.setUpdatedAt(LocalDateTime.now());

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanResponse result = loanService.createLoan(loanRequest);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(new BigDecimal("10000.00"), result.amount());
        assertEquals(Loan.LoanStatus.PENDING, result.status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe actualizar y retornar el préstamo")
    void testUpdateLoan_UpdatesAndReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Optional<LoanResponse> result = loanService.updateLoan(1L, loanRequest);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        verify(loanRepository, times(1)).findById(1L);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe retornar vacío cuando el préstamo no existe")
    void testUpdateLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.updateLoan(999L, loanRequest);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("deleteLoan - Debe eliminar el préstamo sin retornar valor")
    void testDeleteLoan_DeletesLoan() {
        doNothing().when(loanRepository).deleteById(1L);

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("getLoansByStatus - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_FiltersByStatus() {
        List<Loan> pendingLoans = Arrays.asList(loan);
        when(loanRepository.findByStatus(Loan.LoanStatus.PENDING)).thenReturn(pendingLoans);

        List<LoanResponse> result = loanService.getLoansByStatus(Loan.LoanStatus.PENDING);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Loan.LoanStatus.PENDING, result.get(0).status());
        verify(loanRepository, times(1)).findByStatus(Loan.LoanStatus.PENDING);
    }

    @Test
    @DisplayName("approveLoan - Debe aprobar un préstamo pendiente")
    void testApproveLoan_ApprovesPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.APPROVED);
            return l;
        });

        Optional<LoanResponse> result = loanService.approveLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.APPROVED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("approveLoan - Debe retornar vacío cuando el préstamo no existe")
    void testApproveLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.approveLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe rechazar un préstamo pendiente")
    void testRejectLoan_RejectsPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.REJECTED);
            return l;
        });

        Optional<LoanResponse> result = loanService.rejectLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.REJECTED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe retornar vacío cuando el préstamo no existe")
    void testRejectLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.rejectLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("getLoansByAmountRange - Debe filtrar préstamos por rango de monto")
    void testGetLoansByAmountRange_FiltersByAmount() {
        List<Loan> loans = Arrays.asList(loan);
        BigDecimal minAmount = new BigDecimal("5000.00");
        BigDecimal maxAmount = new BigDecimal("15000.00");
        when(loanRepository.findByAmountBetween(minAmount, maxAmount)).thenReturn(loans);

        List<LoanResponse> result = loanService.getLoansByAmountRange(minAmount, maxAmount);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findByAmountBetween(minAmount, maxAmount);
    }

    @Test
    @DisplayName("getLoansByClientId - Debe filtrar préstamos por ID de cliente")
    void testGetLoansByClientId_FiltersByClient() {
        List<Loan> clientLoans = Arrays.asList(loan);
        when(loanRepository.findByClientId(100L)).thenReturn(clientLoans);

        List<LoanResponse> result = loanService.getLoansByClientId(100L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).clientId());
        verify(loanRepository, times(1)).findByClientId(100L);
    }
}