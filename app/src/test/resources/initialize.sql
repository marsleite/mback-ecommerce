CREATE TABLE item (
    sku_id VARCHAR(13) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    user_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS cart (
    id VARCHAR(13) PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    items JSON NOT NULL,
    total DECIMAL(10,2) NOT NULL
);
