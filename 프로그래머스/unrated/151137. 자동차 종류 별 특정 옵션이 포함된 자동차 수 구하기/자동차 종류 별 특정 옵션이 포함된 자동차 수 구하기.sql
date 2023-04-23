select CAR_TYPE, count(*) CARS
from CAR_RENTAL_COMPANY_CAR
where options like "%시트%"
group by CAR_TYPE
order by 1