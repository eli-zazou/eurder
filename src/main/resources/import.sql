-- SET search_path TO eurder;

-- Insert Customers and their users

-- Insert Customer 1
INSERT INTO customer (id, firstname, lastname, emailAddress, streetName, streetNumber, locality, postalCode, phone_number)
VALUES (nextval('customer_seq'), 'John', 'Doe', 'john.doe@example.com', 'Main St', '1', 'City', 1200, '047521603');

INSERT INTO users (id, username, password, role, fk_customer_id)
VALUES (nextval('user_seq'), 'john.doe@example.com', '123', 'CUSTOMER', 1);

-- Insert Customer 2
INSERT INTO customer (id, firstname, lastname, emailAddress, streetName, streetNumber, locality, postalCode, phone_number)
VALUES (nextval('customer_seq'), 'Jane', 'Smith', 'jane.smith@example.com', 'Oak St', '456', 'Town', 1150, '047521604');

INSERT INTO users (id, username, password, role, fk_customer_id)
VALUES (nextval('user_seq'), 'jane.smith@example.com', '123', 'CUSTOMER', 2);

-- Insert Customer 3
INSERT INTO customer (id, firstname, lastname, emailAddress, streetName, streetNumber, locality, postalCode, phone_number)
VALUES (nextval('customer_seq'), 'Eli', 'Jan', 'eli@example.com', 'En bas du bat', '23A', 'Village', 96, '047521605');

INSERT INTO users (id, username, password, role, fk_customer_id)
VALUES (nextval('user_seq'), 'eli@example.com', '123', 'CUSTOMER', 3);

-- Insert Admin User
INSERT INTO users (id, username, password, role, fk_customer_id)
VALUES (nextval('user_seq'), 'admin@admin.be', 'admin', 'ADMIN', null);

-- Insert Items

-- Insert Chocolate Bars
INSERT INTO item (id, name, description, price, amount)
VALUES (nextval('item_seq'), 'Chocolate Bar', 'Delicious milk chocolate bar', 2.5, 3);

-- Insert Java Skills
INSERT INTO item (id, name, description, price, amount)
VALUES (nextval('item_seq'), 'Java Skills', 'You want Java programming skills?', 100000.0, 10);

-- Insert Williams
INSERT INTO item (id, name, description, price, amount)
VALUES (nextval('item_seq'), 'William', 'Switchfully teachers that will evaluate your self Eval', 5999.99, 2);


-- eli (id: 3) order

INSERT INTO eurder (order_date, total_price, fk_customer_id, id)
VALUES ('2023-11-18', 7.5, 3, nextval('eurder_seq'));

INSERT INTO itemgroup (amount, shipping_date, unit_price_at_order, fk_item_id, fk_eurder_id, id)
VALUES (3, '2023-11-19', 2.5, 1, 1, nextval('itemgroup_seq'));

INSERT INTO eurder (order_date, total_price, fk_customer_id, id)
VALUES ('2023-11-18', 5, 3, nextval('eurder_seq'));

INSERT INTO itemgroup (amount, shipping_date, unit_price_at_order, fk_item_id, fk_eurder_id, id)
VALUES (2, '2023-11-19', 2.5, 1, 2, nextval('itemgroup_seq'));

