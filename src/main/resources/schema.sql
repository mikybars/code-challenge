CREATE TABLE product_price (
    id UUID PRIMARY KEY,
    product_id VARCHAR(255) NOT NULL,
    brand_id VARCHAR(255) NOT NULL,
    price_list_id VARCHAR(255) NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    rank BIGINT NOT NULL,
    amount VARCHAR(255) NOT NULL,
    currency VARCHAR(10) NOT NULL
);

CREATE INDEX ON product_price(product_id, brand_id, start_date, end_date, rank);

COMMENT ON COLUMN product_price.rank IS 'number that unties results in case of multiple matches'