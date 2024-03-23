CREATE TABLE products (
    sku_id VARCHAR(13) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    user_id VARCHAR(255)
);
