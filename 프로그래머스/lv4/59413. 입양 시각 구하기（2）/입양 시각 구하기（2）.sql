set @hour=-1;
select @hour := @hour + 1 hour, ifnull((
    select count(*)
    from ANIMAL_OUTS
    where HOUR(DATETIME) = @hour
    group by HOUR(DATETIME)
), 0) COUNT
from ANIMAL_OUTS
where @hour < 23
