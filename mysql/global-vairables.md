-- outputs the active encoding configurations across different
-- layers of your MySQL session, server, and client connections.
SHOW VARIABLES LIKE 'character_set%';

-- displays the active collation settings for your MySQL server,
-- database, client, and current session.Collations dictate the
-- rules for sorting and comparing text strings

-- collation_connection | utf8mb4_0900_ai_ci | literal string comparisons in your queries.
-- collation_database | utf8mb4_0900_ai_ci | Default collation used by the currently selected database.
-- collation_server | utf8mb4_0900_ai_ci | Default fallback collation for the entire MySQL server instance.
SHOW VARIABLES LIKE 'collation%';

-- displays the size of the memory cache MySQL allocates to hold index and row data for InnoDB tables.
SHOW VARIABLES LIKE 'innodb_buffer_pool_size';
-- displays the maximum number of simultaneous client connections your MySQL server will accept.
SHOW VARIABLES LIKE 'max_connections';

-- 현재 MySQL 서버에 접속해 있는 클라이언트의 수
SHOW STATUS LIKE 'Threads_connected';
-- 서버가 마지막으로 시작 된 후부터 지금까지 경과된 시간 (초단위)
SHOW STATUS LIKE 'Uptime';

-- 메모리 사용량 확인
SELECT
(@@innodb_buffer_pool_size / 1024 / 1024) AS buffer_pool_mb,
(@@key_buffer_size / 1024 / 1024) AS key_buffer_mb;

-- 연결 수 조정 (즉시 적용)
SET GLOBAL max_connections = 1000;

-- InnoDB 설정 (재시작 필요)
SET GLOBAL innodb_buffer_pool_size = 4294967296; -- 4GB

-- 설정 확인
SHOW VARIABLES LIKE 'max_connections';

-- 현재 타임존 확인
SELECT @@global.time_zone, @@session.time_zone;

-- 타임존 설정
SET GLOBAL time_zone = '+09:00'; -- KST

-- 데이터베이스별 문자셋 확인
SELECT
SCHEMA_NAME,
DEFAULT_CHARACTER_SET_NAME,
DEFAULT_COLLATION_NAME
FROM information_schema.SCHEMATA;

-- 슬로우 쿼리 로그 설정
SET GLOBAL slow_query_log = 'ON';
SET GLOBAL long_query_time = 2; -- 2초 이상

-- 현재 로그 설정 확인
SHOW VARIABLES LIKE '%log%';
