select ins.animal_id, ins.name
from ANIMAL_INS ins 
join ANIMAL_OUTS outs on ins.animal_id = outs.animal_id
order by datediff(outs.DATETIME, ins.DATETIME) desc
LIMIT 2;