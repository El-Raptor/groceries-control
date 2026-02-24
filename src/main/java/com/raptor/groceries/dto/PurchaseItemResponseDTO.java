package com.raptor.groceries.dto;

import com.raptor.groceries.model.UnitsMeasurement;
import java.math.BigDecimal;

public record PurchaseItemResponseDTO(
        long id,
        ProductResponseDTO product,
        UnitsMeasurement unitsMeasurement,
        BigDecimal quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
}