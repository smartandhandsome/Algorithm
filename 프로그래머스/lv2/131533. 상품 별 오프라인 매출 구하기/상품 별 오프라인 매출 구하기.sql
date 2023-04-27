select PRODUCT_CODE, SUM(PRICE*SALES_AMOUNT) SALES
from OFFLINE_SALE
join product using(product_id)
group by product_id
order by 2 desc, 1