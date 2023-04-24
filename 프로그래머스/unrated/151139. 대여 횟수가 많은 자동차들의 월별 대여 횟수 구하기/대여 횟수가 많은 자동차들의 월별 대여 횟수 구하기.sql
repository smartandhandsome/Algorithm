select MONTH(START_DATE) MONTH, car_id, count(car_id) RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where car_id in (select CAR_ID from CAR_RENTAL_COMPANY_RENTAL_HISTORY where START_DATE between "2022-08-01" and "2022-10-31" group by CAR_ID having count(*) >= 5) and START_DATE between "2022-08-01" and "2022-10-31"
group by MONTH(START_DATE), car_id
having count(car_id) > 0
order by 1, 2 desc