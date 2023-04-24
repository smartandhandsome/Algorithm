select MCDP_CD "진료과코드", count(*) "5월예약건수"
from APPOINTMENT
where date_format(APNT_YMD, "%Y-%m") = "2022-05"
group by MCDP_CD
order by 2, 1