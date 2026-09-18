package com.fintech.loansapi.controller;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import com.fintech.loansapi.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanController")
class LoanControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private Loan loan;
    private LoanResponse loanResponse;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo personal para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanResponse = LoanResponse.fromEntity(loan);
        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo personal para vehículo"
        );
    }

    @Test
    @DisplayName("GET /api/loans - Debe retornar todos los préstamos")
    void testGetAllLoans_ReturnsListOfLoans() throws Exception {
        List<LoanResponse> loans = Arrays.asList(loanResponse);
        when(loanService.getAllLoans()).thenReturn(loans);

        ResultActions result = mockMvc.perform(get("/api/loans"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].id").value(1))
              .andExpect(jsonPath("$[0].amount").value(10000.00))
              .andExpect(jsonPath("$[0].clientId").value(100));
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Debe retornar un préstamo por ID")
    void testGetLoanById_ReturnsLoan() throws Exception {
        when(loanService.getLoanById(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(get("/api/loans/1"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.amount").value(10000.00))
              .andExpect(jsonPath("$.interestRate").value(0.15));
    }

    @Test
    @DisplayName("POST /api/loans - Debe crear un nuevo préstamo")
    void testCreateLoan_ReturnsCreated() throws Exception {
        when(loanService.createLoan(any(LoanRequest.class))).thenReturn(loanResponse);

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isCreated())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(header().exists("Location"))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.clientId").value(100));
    }

    @Test
    @DisplayName("PUT /api/loans/{id} - Debe actualizar un préstamo existente")
    void testUpdateLoan_ReturnsOk() throws Exception {
        when(loanService.updateLoan(eq(1L), any(LoanRequest.class))).thenReturn(loanResponse);

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(put("/api/loans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.description").value("Préstamo personal para vehículo"));
    }

    @Test
    @DisplayName("DELETE /api/loans/{id} - Debe eliminar un préstamo")
    void testDeleteLoan_ReturnsNoContent() throws Exception {
        doNothing().when(loanService).deleteLoan(1L);

        ResultActions result = mockMvc.perform(delete("/api/loans/1"));

        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/loans/status/{status} - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_ReturnsFilteredList() throws Exception {
        List<LoanResponse> pendingLoans = Arrays.asList(loanResponse);
        when(loanService.getLoansByStatus(LoanStatus.PENDING)).thenReturn(pendingLoans);

        ResultActions result = mockMvc.perform(get("/api/loans/status/PENDING"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/approve - Debe aprobar un préstamo")
    void testApproveLoan_ReturnsOk() throws Exception {
        when(loanService.approveLoan(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(patch("/api/loans/1/approve"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/reject - Debe rechazar un préstamo")
    void testRejectLoan_ReturnsOk() throws Exception {
        when(loanService.rejectLoan(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(patch("/api/loans/1/reject"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }
}