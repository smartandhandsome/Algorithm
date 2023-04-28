select distinct car_id
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
join CAR_RENTAL_COMPANY_CAR using(CAR_ID)
where MONTH(START_DATE) = 10 and car_type = "세단"
order by 1 desc