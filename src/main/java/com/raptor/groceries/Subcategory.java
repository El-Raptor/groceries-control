package com.raptor.groceries;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id")
    private long id;

    @Column(name = "subcategory_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
