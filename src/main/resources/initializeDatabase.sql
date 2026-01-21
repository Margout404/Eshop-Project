INSERT INTO store (afm, store_name, owner, password)
VALUES (100000001, 'Tech Galaxy', 'Nikos Papadopoulos', '1234'),
       (100000002, 'Fashion City', 'Maria Georgiou', '1234'),
       (100000003, 'Book Worm', 'Giorgos Dimitriou', '1234'),
       (100000004, 'Sports Center', 'Dimitris Ioannou', '1234'),
       (100000005, 'Home & Deco', 'Eleni Karagianni', '1234'),
       (100000006, 'Super Market Alpha', 'Kostas Vlachos', '1234'),
       (100000007, 'Beauty Store', 'Anna Makri', '1234'),
       (100000008, 'Kids Toys', 'Panos Andreou', '1234'),
       (100000009, 'Pet Shop Love', 'Sofia Papageorgiou', '1234'),
       (100000010, 'Music Corner', 'Christos Nikolaou', '1234');


INSERT INTO citizen (afm, first_name, last_name, email, password)
VALUES (200000001, 'Giannis', 'Antoniou', 'giannis@mail.com', '1234'),
       (200000002, 'Katerina', 'Stergiou', 'katerina@mail.com', '1234'),
       (200000003, 'Vasilis', 'Lampropoulos', 'vasilis@mail.com', '1234'),
       (200000004, 'Despoina', 'Rapti', 'despoina@mail.com', '1234'),
       (200000005, 'Thanasis', 'Oikonomou', 'thanasis@mail.com', '1234'),
       (200000006, 'Ioanna', 'Pappa', 'ioanna@mail.com', '1234'),
       (200000007, 'Petros', 'Samaras', 'petros@mail.com', '1234'),
       (200000008, 'Zoi', 'Kondou', 'zoi@mail.com', '1234'),
       (200000009, 'Alexandros', 'Mitropoulos', 'alex@mail.com', '1234'),
       (200000010, 'Foteini', 'Diamanti', 'foteini@mail.com', '1234');

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('Laptop Pro X', 'Dell', 'High performance laptop with 16GB RAM', 1200.00, 10, 100000001),
       ('Wireless Mouse', 'Logitech', 'Ergonomic silent mouse', 25.00, 50, 100000001),
       ('Gaming Monitor 27"', 'Samsung', '144Hz IPS Monitor', 300.00, 15, 100000001);

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('T-Shirt Cotton', 'Nike', '100% Cotton Black T-Shirt', 20.00, 100, 100000002),
       ('Jeans Slim Fit', 'Levis', 'Classic blue jeans', 60.00, 40, 100000002),
       ('Running Shoes', 'Adidas', 'Comfortable running sneakers', 85.00, 20, 100000002);

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('Java Programming', 'OReilly', 'Complete guide to Java', 45.00, 30, 100000003),
       ('The Great Gatsby', 'Penguin', 'Classic literature', 12.00, 25, 100000003);

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('Basketball', 'Spalding', 'Official size 7 ball', 35.00, 20, 100000004),
       ('Yoga Mat', 'Everlast', 'Non-slip yoga mat', 15.00, 50, 100000004);

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('Desk Lamp', 'IKEA', 'LED Desk Lamp white', 22.00, 15, 100000005),
       ('Coffee Mug Set', 'Zara Home', 'Set of 6 ceramic mugs', 18.00, 10, 100000005);

INSERT INTO item (name, brand, description, price, quantity, store_afm)
VALUES ('Olive Oil 1L', 'Altis', 'Extra Virgin Olive Oil', 12.50, 100, 100000006),
       ('Pasta Spaghetti', 'Barilla', '500g Pack', 1.20, 200, 100000006),
       ('Lipstick Red', 'MAC', 'Matte finish lipstick', 25.00, 30, 100000007),
       ('Lego Car', 'LEGO', 'Speed champions set', 40.00, 12, 100000008),
       ('Dog Food 10kg', 'Royal Canin', 'Premium dog food', 55.00, 8, 100000009),
       ('Electric Guitar', 'Fender', 'Stratocaster copy', 150.00, 5, 100000010);

INSERT INTO purchase_history (citizen_afm, store_afm, product_name, quantity, total_price, date)
VALUES (200000001, 100000001, 'Laptop Pro X', 1, 1200.00, '2024-12-01 10:30:00'),
       (200000001, 100000002, 'T-Shirt Cotton', 2, 40.00, '2024-12-05 15:20:00'),
       (200000002, 100000003, 'Java Programming', 1, 45.00, '2025-01-10 09:00:00'),
       (200000003, 100000006, 'Olive Oil 1L', 2, 25.00, '2025-01-12 11:45:00'),
       (200000004, 100000004, 'Yoga Mat', 1, 15.00, '2025-01-14 18:30:00'),
       (200000005, 100000001, 'Wireless Mouse', 1, 25.00, '2025-01-15 12:00:00'),
       (200000006, 100000008, 'Lego Car', 1, 40.00, '2025-01-16 16:15:00'),
       (200000007, 100000002, 'Running Shoes', 1, 85.00, '2025-01-17 10:00:00'),
       (200000008, 100000005, 'Desk Lamp', 1, 22.00, '2025-01-18 14:20:00'),
       (200000009, 100000006, 'Pasta Spaghetti', 5, 6.00, '2025-01-18 19:00:00');