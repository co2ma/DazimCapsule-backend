ALTER TABLE capsule
    ALTER COLUMN name SET NOT NULL,
    ALTER COLUMN email SET NOT NULL,
    ALTER COLUMN body SET NOT NULL,
    ALTER COLUMN unique_link SET NOT NULL,
    ALTER COLUMN expiration_date SET NOT NULL,
    ALTER COLUMN is_processed TYPE BOOLEAN USING (is_processed::integer::boolean);