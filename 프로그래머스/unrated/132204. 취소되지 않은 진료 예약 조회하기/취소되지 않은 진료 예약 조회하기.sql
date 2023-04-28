select APNT_NO, PT_NAME, PT_NO, A.MCDP_CD, DR_NAME, APNT_YMD
from APPOINTMENT A
join PATIENT P using(PT_NO)
join DOCTOR D on MDDR_ID = DR_ID
where A.MCDP_CD = 'CS' and DATE_FORMAT(APNT_YMD, "%Y-%m-%d") = "2022-04-13" and APNT_CNCL_YN = 'N'
order by 6