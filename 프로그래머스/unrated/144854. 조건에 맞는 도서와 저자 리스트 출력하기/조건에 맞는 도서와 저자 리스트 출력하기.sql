select BOOK_ID, AUTHOR_NAME, DATE_FORMAT(PUBLISHED_DATE, "%Y-%m-%d") PUBLISHED_DATE
from book
join AUTHOR using(author_id)
where category = '경제'
order by 3