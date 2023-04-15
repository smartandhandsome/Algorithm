set @time = -1;

select @time := @time + 1 "HOUR", (select COUNT(HOUR(DATETIME)) from animal_outs where @time = HOUR(DATETIME)) "COUNT"
from animal_outs where @time < 23

