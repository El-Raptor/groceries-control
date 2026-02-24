CREATE SCHEMA IF NOT EXISTS groceries;

CREATE TABLE category (
    category_id BIGSERIAL PRIMARY KEY,
    category_name VARCHAR(255)
);

CREATE TABLE subcategory (
    subcategory_id BIGSERIAL PRIMARY KEY,
    subcategory_name VARCHAR(255),
    category_id BIGINT REFERENCES category(category_id)
);

CREATE TABLE brands (
    brand_id BIGSERIAL PRIMARY KEY,
    brand_name VARCHAR(255)
);

CREATE TABLE grocery_stores (
    store_id SERIAL PRIMARY KEY, -- SERIAL pois na entidade o id é do tipo int
    store_name VARCHAR(255)
);

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY, -- Sem @Column mapeado, então o default é 'id'
    product_name VARCHAR(255),
    brand_id BIGINT REFERENCES brands(brand_id),
    subcategory_id BIGINT REFERENCES subcategory(subcategory_id)
);

CREATE TABLE purchases (
    purchase_id BIGSERIAL PRIMARY KEY,
    purchase_date DATE,
    store_id INT REFERENCES grocery_stores(store_id),
    total_price DECIMAL(15, 2)
);

CREATE TABLE purchase_items (
    item_id BIGSERIAL PRIMARY KEY,
    purchase_id BIGINT REFERENCES purchases(purchase_id),
    product_id BIGINT REFERENCES products(id),
    units_measurement VARCHAR(50), -- Mapeado via @Enumerated(EnumType.STRING)
    quantity DECIMAL(10, 3),       -- 3 casas decimais para cobrir KG
    unit_price DECIMAL(15, 2),
    total_price DECIMAL(15, 2)
);