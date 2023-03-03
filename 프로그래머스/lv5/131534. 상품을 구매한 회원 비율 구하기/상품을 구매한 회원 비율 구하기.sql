SET @total = (SELECT COUNT(USER_ID) FROM USER_INFO WHERE YEAR(JOINED) = 2021);
SELECT 
    YEAR(sales_date) YEAR,
    MONTH(sales_date) MONTH,
    COUNT(DISTINCT O.USER_ID) PUCHASED_USERS,
    ROUND(COUNT(DISTINCT O.USER_ID) / @total, 1) PUCHASED_RATIO
FROM ONLINE_SALE O
WHERE O.USER_ID IN (
    SELECT USER_ID FROM USER_INFO WHERE YEAR(JOINED) = 2021
)
GROUP BY YEAR(sales_date), MONTH(sales_date)
ORDER BY YEAR, MONTH