select ANIMAL_TYPE, count(*) count
from ANIMAL_INS
group by ANIMAL_TYPE
order by 1