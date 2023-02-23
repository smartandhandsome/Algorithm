SELECT PT_NAME, PT_NO, GEND_CD, AGE, IFNULL(TLNO, "NONE") "TLNO"
FROM PATIENT
WHERE GEND_CD = 'W' and AGE <= 12
ORDER BY AGE DESC, PT_NAME;