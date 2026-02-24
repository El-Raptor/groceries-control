package com.raptor.groceries.dto;

import com.raptor.groceries.model.UnitsMeasurement;
import java.math.BigDecimal;

public record PurchaseItemRequestDTO(
        Long productId,
        UnitsMeasurement unitsMeasurement,
        BigDecimal quantity,
        BigDecimal unitPrice
) {
}