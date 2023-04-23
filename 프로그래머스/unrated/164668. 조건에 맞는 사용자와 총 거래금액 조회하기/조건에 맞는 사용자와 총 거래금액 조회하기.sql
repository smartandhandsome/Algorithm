select USER_ID, NICKNAME, sum(price) TOTAL_SALES
from USED_GOODS_BOARD B
join USED_GOODS_USER U on B.writer_id = U.user_id
where status = "done"
group by USER_ID
having sum(price) >= 700000
order by 3