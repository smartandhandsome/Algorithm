select INGREDIENT_TYPE, sum(TOTAL_ORDER) TOTAL_ORDER
from first_half f
join icecream_info i on i.FLAVOR = f.FLAVOR
group by INGREDIENT_TYPE
order by 2