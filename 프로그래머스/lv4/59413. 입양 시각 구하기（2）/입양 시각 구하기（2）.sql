set @time = -1;

select @time := @time +1 "HOUR",
(
    select COUNT(HOUR(DATETIME))
    from ANIMAL_OUTS
    where @time = HOUR(DATETIME)
) COUNT
from ANIMAL_OUTS
where @time < 23