-- 코드를 입력하세요
SELECT u.USER_ID, u.NICKNAME, sum(price) TOTAL_SALES
from used_goods_board b 
join used_goods_user u on b.writer_id = u.user_id
where status = "DONE"
group by u.user_id
having TOTAL_SALES >= 700000
order by 3