package com.raptor.groceries.controller;

import com.raptor.groceries.dto.PurchaseRequestDTO;
import com.raptor.groceries.dto.PurchaseResponseDTO;
import com.raptor.groceries.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> create(@RequestBody PurchaseRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}