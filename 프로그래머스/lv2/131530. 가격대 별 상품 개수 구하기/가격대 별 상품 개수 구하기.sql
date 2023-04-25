select truncate(price / 10000, 0) * 10000 PRICE_GROUP, count(*) PRODUCTS
from PRODUCT
group by (truncate(price / 10000, 0) * 10000)
order by 1