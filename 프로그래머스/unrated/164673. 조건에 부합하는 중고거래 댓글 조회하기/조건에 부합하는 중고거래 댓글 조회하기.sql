-- 코드를 입력하세요
SELECT TITLE, b.BOARD_ID, REPLY_ID, r.WRITER_ID, r.contents, DATE_FORMAT(r.CREATED_DATE, "%Y-%m-%d") CREATED_DATE from USED_GOODS_BOARD b
join USED_GOODS_REPLY r on b.board_id = r.board_id
where YEAR(b.CREATED_DATE) = 2022 and MONTH(b.CREATED_DATE) = 10
order by CREATED_DATE, TITLE