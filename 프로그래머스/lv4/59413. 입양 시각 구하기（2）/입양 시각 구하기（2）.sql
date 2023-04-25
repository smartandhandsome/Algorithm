set @hour=-1;
select @hour := @hour + 1 hour, (
    select count(*)
    from ANIMAL_OUTS
    where HOUR(DATETIME) = @hour
) COUNT
from ANIMAL_OUTS
where @hour < 23