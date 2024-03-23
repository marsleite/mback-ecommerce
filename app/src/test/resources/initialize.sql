CREATE TABLE IF NOT EXISTS item (
    sku_id VARCHAR(13) PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    user_id VARCHAR(100),
);
