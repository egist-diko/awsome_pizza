IF NOT EXISTS (SELECT * FROM sys.triggers WHERE name='trg_set_order_number')
BEGIN

    EXEC('
        CREATE TRIGGER trg_set_order_number
        ON orders
        AFTER INSERT
        AS
        BEGIN
            SET NOCOUNT ON;
            UPDATE Orders
            SET order_number = NEXT VALUE FOR order_number_seq
            FROM Orders o
            INNER JOIN inserted i ON o.id = i.id;
        END;
    ');
END;


IF NOT EXISTS (SELECT * FROM sys.sequences WHERE name='order_number_seq')
BEGIN

    EXEC('
        CREATE SEQUENCE order_number_seq
        START WITH 1
        INCREMENT BY 1;
    ');
END;