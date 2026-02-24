package com.raptor.groceries.dto;

import java.time.LocalDate;
import java.util.List;

public record PurchaseRequestDTO(
        LocalDate purchaseDate,
        Integer storeId,
        List<PurchaseItemRequestDTO> items
) {
}