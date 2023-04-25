select HOUR(DATETIME) HOUR, count(*) count
from ANIMAL_OUTS
group by HOUR
having HOUR between 9 and 19
order by 1