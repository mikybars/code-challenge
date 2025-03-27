INSERT INTO product_price (id, product_id, brand_id, price_list_id, start_date, end_date, rank, amount, currency)
VALUES
  (UUID(), 'p1', 'b1', 'pl1', '2023-01-01 00:00:00', '2023-12-31 23:59:59', 1, '100.00', 'USD'),
  (UUID(), 'p2', 'b1', 'pl1', '2023-01-01 00:00:00', '2023-12-31 23:59:59', 0, '40.00', 'EUR'),
  (UUID(), 'p2', 'b1', 'pl1', '2023-11-20 00:00:00', '2023-11-26 23:59:59', 1, '35.00', 'EUR');
