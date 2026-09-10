-- offset-based, cursor-based pagination
```
Cursor-based pagination avoids the classic OFFSET/LIMIT problem by using a pointer (the "cursor") into the last row you saw, 
instead of counting rows from the start every time.

Why not OFFSET/LIMIT
sql
SELECT * FROM posts ORDER BY created_at DESC LIMIT 20 OFFSET 10000;

This looks simple, but the database still has to scan and discard the first 10,000 rows before returning your page. Two problems:

Performance degrades linearly with offset — page 500 is much slower than page 1.
Instability under writes — if a row is inserted or deleted while someone is paginating, rows shift position, causing skipped or duplicated results between pages.
How cursor pagination works

Instead of "skip N rows," you say "give me rows after this specific row." The cursor is usually the value(s) of the column(s) you're sorting by, taken from the last row of the previous page.
```
```sql
SELECT
post_id,
title,
view_count,
created_at
FROM posts
WHERE status = 'PUBLISHED'
ORDER BY created_at DESC
LIMIT 10 OFFSET 0;

SELECT
post_id,
title,
view_count,
created_at
FROM posts
WHERE status = 'PUBLISHED'
ORDER BY created_at DESC
LIMIT 10 OFFSET 10;


SELECT post_id, title, created_at
FROM posts
WHERE status = 'PUBLISHED'
ORDER BY created_at DESC
LIMIT 10 OFFSET 10000;


-- cursor-based pagination
SELECT post_id, title, created_at
FROM posts
WHERE status = 'PUBLISHED'
ORDER BY created_at DESC, post_id DESC
LIMIT 10;

-- 커서 기반의 페이징 두번쨰 페이지
SELECT post_id, title, created_at
FROM posts
WHERE status = 'PUBLISHED' AND post_id < 12345
ORDER BY created_at DESC, post_id DESC
LIMIT 10;
```


```sql
SELECT
기본컬럼들,
윈도우함수() OVER (PARTITION BY 그룹컬럼 ORDER BY 정렬컬럼) as 결과컬럼
FROM 테이블;
```


```sql
SELECT
post_id,
title,
view_count,

    -- 순차적인 순위 (1,2,3,4...)
    ROW_NUMBER() OVER (ORDER BY view_count DESC) as 전체순위,

    -- 동점자 고려한 순위 (1,2,2,4...)
    RANK() OVER (ORDER BY view_count DESC) as 랭킹

FROM posts
WHERE status = 'PUBLISHED'
ORDER BY view_count DESC;

SELECT
user_id,
post_id,
title,
view_count,

    -- 각 사용자 안에서의 순위
    ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY view_count DESC) as 사용자내순위

FROM posts
WHERE status = 'PUBLISHED'
ORDER BY user_id, 사용자내순위;


SELECT
post_id,
title,
created_at,
view_count,

    -- 이전 게시글의 조회수
    LAG(view_count, 1) OVER (ORDER BY created_at) as 이전글조회수,

    -- 다음 게시글의 조회수
    LEAD(view_count, 1) OVER (ORDER BY created_at) as 다음글조회수

FROM posts
WHERE status = 'PUBLISHED'
ORDER BY created_at;
```

```sql
WITH 임시테이블명 AS (
SELECT ... -- 1단계 계산
)
SELECT ...     -- 2단계에서 1단계 결과 사용
FROM 임시테이블명;

WITH post_summary AS (
-- 1단계: 사용자별 게시글 통계 계산
SELECT
user_id,
COUNT(*) as 게시글수,
SUM(view_count) as 총조회수,
AVG(view_count) as 평균조회수
FROM posts
WHERE status = 'PUBLISHED'
GROUP BY user_id
)
-- 2단계: 사용자 정보와 결합해서 최종 결과
SELECT
u.username,
ps.게시글수,
ps.총조회수,
ps.평균조회수
FROM post_summary ps
INNER JOIN users u ON ps.user_id = u.user_id
ORDER BY ps.총조회수 DESC;

SELECT
u.username,
COUNT(*) as 게시글수,
SUM(p.view_count) as 총조회수,
AVG(p.view_count) as 평균조회수
FROM posts p
INNER JOIN users u ON p.user_id = u.user_id
WHERE p.status = 'PUBLISHED'
GROUP BY u.user_id, u.username
ORDER BY SUM(p.view_count) DESC;
```



```sql
WITH 활성사용자 AS (
-- 1단계: 활성 사용자만 추출
SELECT user_id, username
FROM users
WHERE status = 'ACTIVE'
),
최근게시글 AS (
-- 2단계: 최근 30일 게시글 통계
SELECT user_id, COUNT(*) as 최근글수
FROM posts
WHERE created_at >= DATE_SUB(NOW(), INTERVAL 30 DAY)
AND status = 'PUBLISHED'
GROUP BY user_id
)
-- 3단계: 두 결과를 조합
SELECT
a.username,
COALESCE(r.최근글수, 0) as 최근30일글수
FROM 활성사용자 a
LEFT JOIN 최근게시글 r ON a.user_id = r.user_id
ORDER BY 최근30일글수 DESC;
```