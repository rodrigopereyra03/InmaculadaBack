
INSERT INTO address (id, street, number, zip_code, city, state) VALUES
(2, 'Calle Falsa', 123, 1001, 'Buenos Aires', 'CABA'),
(3, 'Av. Siempre Viva', 742, 2002, 'Córdoba', 'Córdoba');

-- Relacionar direcciones con usuarios (mediante user_id en la tabla address)


ALTER TABLE product ALTER COLUMN main_image TYPE VARCHAR(500);

ALTER TABLE product_images ALTER COLUMN image TYPE VARCHAR(500);

ALTER TABLE categories ALTER COLUMN main_image TYPE VARCHAR(500);



