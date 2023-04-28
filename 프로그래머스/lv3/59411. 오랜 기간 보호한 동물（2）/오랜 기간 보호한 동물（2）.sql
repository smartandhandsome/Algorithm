select ANIMAL_ID, I.NAME
from ANIMAL_INS I
join ANIMAL_OUTS O using(animal_id)
order by datediff(O.DATETIME, I.DATETIME) desc
limit 2;
