INSERT INTO product_price (id, product_id, brand_id, price_list_id, start_date, end_date, rank, amount, currency)
VALUES
  (UUID(), '35455', '1', '1', '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, '35.50', 'EUR'),
  (UUID(), '35455', '1', '2', '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, '25.45', 'EUR'),
  (UUID(), '35455', '1', '3', '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, '30.50', 'EUR'),
  (UUID(), '35455', '1', '4', '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, '38.95', 'EUR');
