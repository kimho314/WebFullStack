-- 연결 상태 상세 정보
SELECT ID, USER, HOST, DB, COMMAND, TIME, STATE, LEFT(INFO, 100) as QUERY_SNIPPET
FROM information_schema.PROCESSLIST
WHERE COMMAND != 'Sleep'
ORDER BY TIME DESC;

-- 버퍼 풀 히트율 확인 (95% 이상이 목표)
SELECT ROUND(
(1 - (
(SELECT VARIABLE_VALUE FROM performance_schema.global_status WHERE VARIABLE_NAME = 'Innodb_buffer_pool_reads') /
(SELECT VARIABLE_VALUE FROM performance_schema.global_status WHERE VARIABLE_NAME = 'Innodb_buffer_pool_read_requests')
)) \* 100, 2
) AS buffer_pool_hit_ratio_percent;

use mysql_fundamentals;

SHOW TABLE STATUS LIKE 'users';
SHOW CREATE TABLE users;
SHOW INDEX FROM users;

EXPLAIN SELECT \*
FROM accounts
WHERE YEAR(created_at) = 2023;

EXPLAIN SELECT \* FROM accounts WHERE user_id = 123;
