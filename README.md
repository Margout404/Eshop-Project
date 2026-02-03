# E-Shop System

## Overview

This project was developed as part of the **Msc in Applied Informatics** at the  
**University of Macedonia (ΠΑΜΑΚ – University of Macedonia)**,  
for the course **ΑΝΑΠΤΥΞΗ ΥΠΗΡΕΣΙΩΝ ΝΕΦΟΥΣ**.


The application is a simple **E-Shop system** that demonstrates the design and implementation of RESTful services, proper entity modeling, and interaction between backend and frontend components.

## Developers
- Marios Goutidis mai26012  
- Dimitrios Sideridis mai26066
---

## Features

The system supports two main user roles:

### Citizen (Customer)
- Registration and login (simple credential check – no authentication framework)
- Product search by:
  - name
  - price range
- Add products to cart
- Remove products from cart
- View cart total price
- Checkout (purchase completion with stock update)

### Store
- Registration and login
- Add new products
- View store products
- Update product quantity (stock management)

---

## Technologies Used

- **Backend:** Java, Spring Boot, Spring Data JPA  
- **Database:** MySQL  
- **Frontend:** HTML, CSS, Vanilla JavaScript  
- **API Testing & Documentation:** Swagger  

---

## System Design

- The shopping cart is implemented using a dedicated **CartItem** entity, acting as a join table between `Cart` and `Item`.
- Product stock quantity is clearly separated from the quantity selected by a customer in the cart.
- DTOs and Mappers are used to separate domain models from API responses.
- `@Transactional` is applied where needed (e.g. checkout process) to ensure data consistency.
- `JOIN FETCH` is used in selected queries to optimize performance and avoid the N+1 problem.

---

