package com.fintech.loansapi.controller;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Gestión de Préstamos", description = "API para operaciones CRUD de préstamos")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Obtener todos los préstamos", description = "Retorna una lista de todos los préstamos registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Obtener préstamo por ID", description = "Retorna un préstamo específico basado en su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "ID de préstamo inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id) {
        LoanResponse loan = loanService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @Operation(summary = "Crear nuevo préstamo", description = "Registra un nuevo préstamo en el sistema con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de préstamo inválidos"),
        @ApiResponse(responseCode = "422", description = "Error de validación de datos")
    })
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(
            @Parameter(description = "Datos del préstamo a crear", required = true)
            @Valid @RequestBody LoanRequest request) {
        LoanResponse createdLoan = loanService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    @Operation(summary = "Actualizar préstamo existente", description = "Actualiza los datos de un préstamo existente basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de préstamo inválidos"),
        @ApiResponse(responseCode = "422", description = "Error de validación de datos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> updateLoan(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados del préstamo", required = true)
            @Valid @RequestBody LoanRequest request) {
        LoanResponse updatedLoan = loanService.update(id, request);
        return ResponseEntity.ok(updatedLoan);
    }

    @Operation(summary = "Eliminar préstamo", description = "Elimina un préstamo del sistema basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Préstamo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "ID de préstamo inválido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar préstamos por estado", description = "Retorna todos los préstamos que coinciden con el estado especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Estado de préstamo inválido")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanResponse>> getLoansByStatus(
            @Parameter(description = "Estado del préstamo (PENDING, APPROVED, REJECTED)", required = true, example = "APPROVED")
            @PathVariable String status) {
        List<LoanResponse> loans = loanService.findByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Buscar préstamos por rango de monto", description = "Retorna préstamos cuyo monto está dentro del rango especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Rango de monto inválido")
    })
    @GetMapping("/amount")
    public ResponseEntity<List<LoanResponse>> getLoansByAmountRange(
            @Parameter(description = "Monto mínimo", required = true, example = "1000.00")
            @RequestParam BigDecimal minAmount,
            @Parameter(description = "Monto máximo", required = true, example = "50000.00")
            @RequestParam BigDecimal maxAmount) {
        List<LoanResponse> loans = loanService.findByAmountBetween(minAmount, maxAmount);
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Buscar préstamos por cliente", description = "Retorna todos los préstamos asociados a un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "ID de cliente inválido")
    })
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<LoanResponse>> getLoansByClient(
            @Parameter(description = "ID único del cliente", required = true, example = "100")
            @PathVariable Long clientId) {
        List<LoanResponse> loans = loanService.findByClientId(clientId);
        return ResponseEntity.ok(loans);
    }
}