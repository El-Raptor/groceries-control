package com.raptor.groceries.service;

import com.raptor.groceries.dto.*;
import com.raptor.groceries.model.*;
import com.raptor.groceries.repository.GroceryStoreRepository;
import com.raptor.groceries.repository.ProductRepository;
import com.raptor.groceries.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;
    private final GroceryStoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final GroceryStoreService storeService;

    @Transactional
    public PurchaseResponseDTO create(PurchaseRequestDTO dto) {
        GroceryStore store = storeRepository.findById(dto.storeId())
                .orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        Purchase purchase = Purchase.builder()
                .purchaseDate(dto.purchaseDate())
                .store(store)
                .build();

        // Map items and calculate their total prices
        List<PurchaseItem> items = dto.items().stream().map(itemDto -> {
            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            BigDecimal itemTotalPrice = itemDto.quantity().multiply(itemDto.unitPrice());

            return PurchaseItem.builder()
                    .purchase(purchase) // Relacionamento bidirecional essencial!
                    .product(product)
                    .unitsMeasurement(itemDto.unitsMeasurement())
                    .quantity(itemDto.quantity())
                    .unitPrice(itemDto.unitPrice())
                    .totalPrice(itemTotalPrice)
                    .build();
        }).collect(Collectors.toList());

        purchase.setItems(items);

        // Calculate purchase total value by summing all items total
        BigDecimal purchaseTotal = items.stream()
                .map(PurchaseItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        purchase.setTotalPrice(purchaseTotal);

        Purchase savedPurchase = repository.save(purchase);
        return toResponseDTO(savedPurchase);
    }

    private PurchaseResponseDTO toResponseDTO(Purchase purchase) {
        GroceryStoreResponseDTO storeDto = storeService.toResponseDTO(purchase.getStore());

        List<PurchaseItemResponseDTO> itemsDto = purchase.getItems().stream()
                .map(item -> new PurchaseItemResponseDTO(
                        item.getId(),
                        productService.toResponseDTO(item.getProduct()),
                        item.getUnitsMeasurement(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotalPrice()
                )).collect(Collectors.toList());

        return new PurchaseResponseDTO(
                purchase.getId(),
                purchase.getPurchaseDate(),
                storeDto,
                itemsDto,
                purchase.getTotalPrice()
        );
    }
}