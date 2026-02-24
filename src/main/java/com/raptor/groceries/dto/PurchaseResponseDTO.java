package com.raptor.groceries.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PurchaseResponseDTO(
        long id,
        LocalDate purchaseDate,
        GroceryStoreResponseDTO store,
        List<PurchaseItemResponseDTO> items,
        BigDecimal totalPrice
) {
}