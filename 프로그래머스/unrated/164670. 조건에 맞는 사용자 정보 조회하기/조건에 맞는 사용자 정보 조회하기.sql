select USER_ID, NICKNAME, concat(city, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) 전체주소, concat(left(TLNO, 3), "-", substring(TLNO, 4, 4), "-", right(TLNO, 4)) 전화번호
from USED_GOODS_BOARD B
join USED_GOODS_USER U on B.WRITER_ID = U.USER_ID
group by 1
having count(*) >= 3
order by 1 desc