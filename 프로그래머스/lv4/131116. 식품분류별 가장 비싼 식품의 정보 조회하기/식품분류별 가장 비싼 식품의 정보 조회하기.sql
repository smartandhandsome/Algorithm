select category, price MAX_PRICE, product_name
from FOOD_PRODUCT
where (category, price) in (select CATEGORY, max(price) from FOOD_PRODUCT group by CATEGORY having CATEGORY in ('과자', '국', '김치', '식용유'))
order by 2 desc