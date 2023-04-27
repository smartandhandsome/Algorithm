set @num = (select MEMBER_ID from REST_REVIEW group by MEMBER_ID order by count(*) desc limit 1);

select MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, "%Y-%m-%d") REVIEW_DATE
from MEMBER_PROFILE
join REST_REVIEW using(MEMBER_ID)
where member_id = @num
order by 3, 2