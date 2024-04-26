# 기능 리스트

1. 클라이언트 가입 API

- 회원 가입을 위한 API 입니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: POST /signup, request_body(userName,password,email,role)
    SERVER ->> H2: insert member, token
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response(access-token)
```

2. 로그인 API

- 회원 로그인을 위한 API 입니다
- 가입 후 응답값으로 받은 access-token을 header에다가 넘겨주시면 됩니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: POST /login, header(X-API-TOKEN : access-token), request_body(userName,password)
    SERVER ->> H2: insert token
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response(access-token, refresh-token)
```

3. 로그아웃 API

- 로그아웃을 위한 API 입니다
- 로그인 후 응답값으로 받은 access-token을 header에다가 넘겨주시면 됩니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: PUT /logout, header(X-API-TOKEN : access-token)
    SERVER ->> H2: update token
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response
```

4. 클라이언트 탈퇴 API

- 탈퇴를 위한 API 입니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: DELETE /signout, header(X-API-TOKEN : access-token)
    SERVER ->> H2: update Member
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response
```

5. 클라이언트 토큰 재발급 API

- 유효 기간이 만료된 토큰 재발급용 API 입니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: PUT /reissue-token, request_body(userName, token)
    SERVER ->> H2: update Token
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response(token, tokenType)
```

6. 클라이언트 영상 분석 수 연장 API

- 영상 분석 수 연장 API 입니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: PUT /api/extend-max-analysis-count, header(X-API-TOKEN), request_body(extendCount)
    SERVER ->> H2: update Member
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response(curCount, maxCount)
```

7. 영상 분석 API

- 영산 분석 API 입니다

```mermaid
sequenceDiagram
    CLIENT ->> SERVER: POST /api/dicom-analysis, header(X-API-TOKEN), request_body(frontal)
    SERVER ->> H2: insert DICOM_ANALYZE_RESULT, update MEMBER
    H2 -->> SERVER: response
    SERVER -->> CLIENT: response(predictionTime, score)
```

# 실행 방법

- docker-compose.yml이 위치한 디렉토리로 이동합니다
- 이동한 디렉토리에서 docker-compose up 명령어를 실행합니다
- http://localhost:8080로 호출하면 됩니다

# ERD

```mermaid
erDiagram
    MEMBER ||--|{ TOKEN: uses
    MEMBER ||--|{ DICOM_ANALYZE_RESULT: contains
```