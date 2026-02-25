package com.raptor.groceries.controller;

import com.raptor.groceries.dto.SubcategoryRequestDTO;
import com.raptor.groceries.dto.SubcategoryResponseDTO;
import com.raptor.groceries.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubcategoryController {

    private final SubcategoryService service;

    @PostMapping
    public ResponseEntity<SubcategoryResponseDTO> create(@RequestBody SubcategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }
}