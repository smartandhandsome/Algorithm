SET @top = (select count(*) from REST_REVIEW group by MEMBER_ID order by 1 desc limit 1);

select MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, "%Y-%m-%d") REVIEW_DATE
from MEMBER_PROFILE
join REST_REVIEW using(member_id)
where member_id in (select member_id from REST_REVIEW group by MEMBER_ID having count(*) = @top)
order by 3, 2

