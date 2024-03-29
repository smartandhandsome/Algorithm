select A.REST_ID, REST_NAME, FOOD_TYPE, FAVORITES, ADDRESS, ROUND(SUM(REVIEW_SCORE) / COUNT(REVIEW_SCORE), 2) SCORE
from REST_INFO A
join REST_REVIEW B on A.REST_ID = B.REST_ID
where ADDRESS like "서울%"
group by A.REST_ID
order by SCORE desc, FAVORITES desc