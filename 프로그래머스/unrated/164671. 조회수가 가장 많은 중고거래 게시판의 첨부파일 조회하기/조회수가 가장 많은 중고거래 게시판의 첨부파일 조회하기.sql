select concat("/home/grep/src/", BOARD_ID, "/", FILE_ID, FILE_NAME, FILE_EXT) FILE_PATH
from USED_GOODS_BOARD B
join USED_GOODS_FILE F using(BOARD_ID)
where VIEWS = (select MAX(VIEWS) from USED_GOODS_BOARD)
order by FILE_ID desc

