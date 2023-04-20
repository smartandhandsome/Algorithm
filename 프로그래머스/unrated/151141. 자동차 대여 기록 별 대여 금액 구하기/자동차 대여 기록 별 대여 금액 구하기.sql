select history_id, truncate((datediff(end_date, start_date)+1) * car.daily_fee * 
 case
    when datediff(end_date, start_date) >= 90
    then (select 100- discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where DURATION_TYPE = "90일 이상" and CAR_TYPE = "트럭")
    when datediff(end_date, start_date) >= 30
    then (select 100 - discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where DURATION_TYPE = "30일 이상" and CAR_TYPE = "트럭")
    when datediff(end_date, start_date) >= 7
    then (select 100 - discount_rate from CAR_RENTAL_COMPANY_DISCOUNT_PLAN where DURATION_TYPE = "7일 이상" and CAR_TYPE = "트럭")
    else 100
end / 100, 0) fee
from CAR_RENTAL_COMPANY_CAR car
join CAR_RENTAL_COMPANY_RENTAL_HISTORY his on car.CAR_ID = his.CAR_ID
where car.car_type = "트럭"
order by 2 desc, 1 desc

