SELECT DATE_FORMAT(DATETIME, "%H") HOUR, COUNT(*) COUNT
FROM ANIMAL_OUTS
WHERE DATE_FORMAT(DATETIME, "%H") BETWEEN 9 and 19
GROUP BY 1
ORDER BY 1;

