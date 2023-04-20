-- 코드를 입력하세요

select USER_ID, NICKNAME, concat(CITY, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) '전체주소', concat(left(TLNO, 3), "-", substring(TLNO, 4, 4), "-", right(TLNO, 4)) '전화번호'
from USED_GOODS_USER
where USER_ID in (
    SELECT WRITER_ID
    from used_goods_board 
    group by WRITER_ID
    having count(*) >= 3
)
order by 1 desc
