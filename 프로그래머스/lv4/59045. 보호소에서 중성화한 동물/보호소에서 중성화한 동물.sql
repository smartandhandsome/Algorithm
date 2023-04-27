select ANIMAL_ID, O.ANIMAL_TYPE, O.NAME
from ANIMAL_INS I
join ANIMAL_OUTS O using(ANIMAL_ID)
where SEX_UPON_INTAKE like "INTACT%" and (SEX_UPON_OUTCOME like "Spayed%" or SEX_UPON_OUTCOME like "Neutered%")