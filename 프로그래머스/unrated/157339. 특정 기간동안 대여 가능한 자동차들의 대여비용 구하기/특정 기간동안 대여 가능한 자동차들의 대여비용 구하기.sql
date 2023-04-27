select CAR_ID, car.CAR_TYPE, truncate(daily_fee * 30 * (100 - discount_rate) / 100 , 0) FEE
from CAR_RENTAL_COMPANY_CAR car
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN plan on car.car_type = plan.car_type
where car_id not in 
(
    select car.car_id
    from CAR_RENTAL_COMPANY_CAR car
    join CAR_RENTAL_COMPANY_RENTAL_HISTORY his on car.car_id = his.car_id
    where "2022-11-01" between start_date and end_date or "2022-11-30" between start_date and end_date
)
and duration_type like "30%"
and car.car_type in ('세단', 'SUV')
and truncate(daily_fee * 30 * (100 - discount_rate) / 100 , 0) >= 500000 and truncate(daily_fee * 30 * (100 - discount_rate) / 100 , 0) < 2000000
order by 3 desc, 2, 1 desc