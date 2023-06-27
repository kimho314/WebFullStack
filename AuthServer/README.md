# 인증/회원 서버

## 기능 목록
* 회원 가입
* 회원 리스트 조회
* 회원 조회
* 로그인
* 로그아웃
* 엑세스 토큰 교환
* 엑세스 토큰 체크

## 기술 스펙
* 구현하기 쉬운 spring mvc 이용
* postgresql
* spring data jpa
* spring security

## DB table list
* member
  * id
  * user_id
  * name
  * phone_number
  * password
  * address
* token
  * id
  * member_id
  * access_token
  * refresh_token
  * access_token_expired_at
  * refresh_token_expired_at