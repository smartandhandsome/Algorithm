-- 코드를 입력하세요
SELECT concat("/home/grep/src/", b.BOARD_ID, "/", FILE_ID, FILE_NAME, FILE_EXT) file_path
from used_goods_board b
join used_goods_file f
on b.board_id = f.board_id
where b.views = (
    select *
    from (select views
    from used_goods_board
    order by views desc limit 1) as sub
) 
order by file_id desc