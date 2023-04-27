select PRODUCT_ID, PRODUCT_NAME, (price * AMOUNT) TOTAL_SALES
from FOOD_PRODUCT
join 
(
    select PRODUCT_ID, SUM(AMOUNT) AMOUNT
    from FOOD_ORDER
    where YEAR(PRODUCE_DATE) = "2022" and MONTH(PRODUCE_DATE) = "5"
    group by PRODUCT_ID
) F using(PRODUCT_ID)
order by 3 desc, 1