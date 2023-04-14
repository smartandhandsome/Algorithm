-- 코드를 입력하세요
SELECT BOARD_ID, WRITER_ID, TITLE, PRICE,
case
when status = 'SALE'
then '판매중'
when status = 'DONE'
then '거래완료'
when status = 'RESERVED'
then '예약중' end STATUS
from USED_GOODS_BOARD
where DATE_FORMAT(CREATED_DATE, '%Y-%m-%d') = '2022-10-05'
order by 1 desc
