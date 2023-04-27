select F.FLAVOR
from (select FLAVOR, SUM(TOTAL_ORDER) T from FIRST_HALF group by flavor) F
join (select FLAVOR, SUM(TOTAL_ORDER) T from JULY group by flavor) J on F.FLAVOR = J.FLAVOR
order by (F.T + J.T) desc
limit 3