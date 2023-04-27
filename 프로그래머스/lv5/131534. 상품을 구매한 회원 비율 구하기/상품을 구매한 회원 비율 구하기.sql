set @count = (select count(*) from user_info where year(joined) = "2021");

select YEAR(SALES_DATE) YEAR, MONTH(SALES_DATE) MONTH,  count(distinct user_id) PUCHASED_USERS, round(count(distinct user_id) / @count, 1) PUCHASED_RATIO
from ONLINE_SALE
where user_id in (select user_id from user_info where year(joined) = "2021")
group by 1, 2
