# Microservices Backend Project Ecommerce

## Description

This project is a Spring Boot application written in Kotlin and Java. It uses SQL for data persistence and Gradle for build and dependency management.

## Technologies

- Kotlin
- Java
- Coroutine
- Spring Boot 3.2.4
- SQL
- Gradle

## Endpoints

### Item Endpoints

- POST /mback/item: Create a new item
- PUT /mback/item: Update an existing item
- DELETE /mback/item/{skuId}: Delete an item by SKU ID
- GET /mback/item/{skuId}: Get an item by SKU ID
- GET /mback/item: Get all items

### Cart Endpoints

- POST /mback/cart/{userId}: Create a new shopping cart for a user

## How to Run

1. Clone the repository
2. Navigate to the project directory
3. Run the following command to build the project:

```bash
./gradlew build
```

----------------

# Microservices Backend Project Ecommerce (portuguese

## Descrição

Este projeto é uma aplicação Spring Boot escrita em Kotlin e Java. Ele usa SQL para persistência de dados e Gradle para gerenciamento de build e dependências.

## Tecnologias

- Kotlin
- Java
- Spring Boot
- SQL
- Gradle

## Endpoints

### Endpoints de Item

- POST /mback/item: Cria um novo item
- PUT /mback/item: Atualiza um item existente
- DELETE /mback/item/{skuId}: Deleta um item pelo SKU ID
- GET /mback/item/{skuId}: Obtém um item pelo SKU ID
- GET /mback/item: Obtém todos os itens

### Endpoints de Carrinho

- POST /mback/cart/{userId}: Cria um novo carrinho de compras para um usuário

## Como Executar

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Execute o seguinte comando para construir o projeto:

```bash
./gradlew build
```