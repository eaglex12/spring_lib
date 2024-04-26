# Book Library Management System

This is a Spring Boot application that provides RESTful endpoints to manage a book library.

## Requirements

1. **Data Models**:
    - **`Book`**: Contains fields like **`id`**, **`title`**, **`author`**, **`isbn`**, and **`publicationYear`**.
    - **`Author`**: Contains fields like **`id`**, **`name`**, and **`biography`**.
    - **`Rental`**: Represents a book rental, with fields **`id`**, **`bookId`**, **`renterName`**, **`rentalDate`**, and **`returnDate`**.

2. **RESTful Endpoints**:
    - CRUD operations for **`Book`**.
    - CRUD operations for **`Author`**.
    - Create and retrieve **`Rental`** records.
    - Endpoints to retrieve books by author, books available for rent, and books currently rented.

3. **Business Logic**:
    - When renting a book, ensure the book is not already rented logic is there.
    - When returning a book, marking it as available is also there
    - Implement a method to check for overdue rentals (rental periods exceeding a set time, e.g., 14 days) is also implemneted.


## H2 Console Configuration

For this application, I've configured the H2 in-memory database. You can access the H2 console to view and interact with the database.

## Technologies Used

- Spring Boot
- MySQL
- Maven

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/book-library-management.git
    ```

2. Navigate to the project directory:
    ```bash
    cd book-library-management
    ```

3. Build and run the application:
    ```bash
    mvn spring-boot:run
    ```

## API Documentation

- Detailed API documentation will be provided in the `API_DOCUMENTATION.md` file.
