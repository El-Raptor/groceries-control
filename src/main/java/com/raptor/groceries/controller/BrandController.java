package com.raptor.groceries.controller;

import com.raptor.groceries.dto.BrandRequestDTO;
import com.raptor.groceries.dto.BrandResponseDTO;
import com.raptor.groceries.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService service;

    @PostMapping
    public ResponseEntity<BrandResponseDTO> create(@RequestBody BrandRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}