-- 데이터 베이스 생성
CREATE DATABASE financial_app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
-- 데이터 베이스 확인
SHOW DATABASES;
-- 데이터베이스 정보 상세 확인
SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME
FROM information_schema.SCHEMATA WHERE SCHEMA_NAME = 'financial_app';
-- 데이터베이스 사용
USE financial_app;

-- 애플리케이션용 사용자 생성
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'secure_password';
CREATE USER 'app_user'@'%' IDENTIFIED BY 'secure_password'; -- 원격 접속
-- 사용자 목록 확인
SELECT User, Host FROM mysql.user;

-- 애플리케이션 사용자 권한 부여
GRANT SELECT, INSERT, UPDATE, DELETE ON financial_app.\* TO 'app_user'@'%';
-- 권한 확인
SHOW GRANTS FOR 'app_user'@'%';
-- 권한 적용
FLUSH PRIVILEGES;
