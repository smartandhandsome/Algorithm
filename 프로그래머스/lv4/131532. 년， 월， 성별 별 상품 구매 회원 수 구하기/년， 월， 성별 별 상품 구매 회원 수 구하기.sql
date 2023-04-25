select distinct YEAR(sales_date) year, MONTH(sales_date) month, gender, COUNT(distinct s.USER_ID) users
from ONLINE_SALE s
join USER_INFO u on s.USER_ID = u.USER_ID
group by YEAR(sales_date), MONTH(sales_date), gender
having gender is not null
order by 1, 2, 3
