DELIMITER //

CREATE PROCEDURE GetMostPurchasesProduct()
BEGIN
    SELECT p.ID, p.NAME, p.DESCRIPTION, p.STOCK, p.PRICE, p.AVAILABLE, p.CREATE_DATE, p.UPDATE_DATE
    FROM PRODUCT p
    JOIN SALES s ON p.ID = s.PRODUCT_ID
    GROUP BY p.ID
    ORDER BY SUM(s.QUANTITY) DESC
    LIMIT 1;
END //

DELIMITER ;
