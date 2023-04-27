select ANIMAL_ID, O.NAME
from ANIMAL_INS I
right join ANIMAL_OUTS O using(ANIMAL_ID)
where I.ANIMAL_ID is null and O.ANIMAL_ID is not null
