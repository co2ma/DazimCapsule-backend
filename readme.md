# 다짐 캡슐 backend(DazimCapsule)

> 미래의 나에게 보내는 편지 — 지금의 다짐을 캡슐에 담아, 정해진 날 자동으로 전달합니다.

---

## 프로젝트 소개

**다짐 캡슐**은 타임캡슐 형식의 편지 서비스입니다.

사용자가 고유 링크를 생성하면, 지인들이 그 링크를 통해 편지(메시지)를 남길 수 있습니다.
설정한 만료일이 되면 스케줄러가 자동으로 실행되어, 수신된 모든 편지를 한 통의 이메일로 묶어 전송합니다.

### 서비스 흐름

```
1. 사용자가 캡슐 링크 생성 (만료일 설정)
       ↓
2. 링크를 지인들에게 공유
       ↓
3. 지인들이 링크를 통해 편지 작성 및 제출
       ↓
4. 만료일 당일 새벽, 스케줄러 자동 실행
       ↓
5. 등록된 모든 편지를 묶어 이메일 발송
```

---

## 기술 스택

| 분류 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 4.0.6 |
| ORM | Spring Data JPA (Hibernate) |
| Database | PostgreSQL |
| DB Migration | Flyway |
| Batch/Scheduling | Spring Batch, Spring Scheduling |
| Mail | Spring Mail (SMTP) |
| API Docs | SpringDoc OpenAPI 3.0 (Swagger) |
| Build | Gradle |
| Config | springboot4-dotenv (.env 파일 지원) |

---

## 주요 기능

### 1. 캡슐 링크 생성
- 고유 링크와 만료일을 설정하여 캡슐 생성
- `POST /makeForm`

### 2. 편지 등록
- 링크 유효성 확인 후 이름, 이메일, 메시지 제출
- 링크당 동일 이메일로 중복 제출 불가
- `GET /form/{link}` — 링크 유효성 확인
- `POST /form/{link}` — 편지 제출

### 3. 자동 이메일 발송
- 매일 새벽 00:30 스케줄러 실행 (`0 30 0 * * *`)
- 만료일이 도래한 캡슐의 미처리 편지를 모아 한 통의 HTML 이메일로 발송
- 발송 후 처리 완료 상태로 업데이트 (중복 발송 방지)

---

## 프로젝트 구조

```
src/main/java/com/co2ma/dazimcapsule/
├── capsule/                  # 캡슐 링크 & 편지 관리 (Entity, Service, Controller, DTO)
├── uniqueLinkGenerate/       # 고유 링크 생성 로직
├── scheduler/                # 이메일 스케줄러 & 배치 서비스
│   ├── EmailScheduler        # Cron 기반 스케줄 트리거
│   ├── EmailBatchService     # 만료 캡슐 조회 및 배치 처리
│   └── EmailSendService      # HTML 이메일 발송
└── config/                   # CORS 등 애플리케이션 설정

src/main/resources/
├── application.yaml          # 환경 설정
└── db/migration/             # Flyway 마이그레이션 스크립트 (V1~V5)
```

---

## 데이터베이스 스키마

```sql
-- 캡슐 링크 (생성자 정보 및 만료일)
capsule_link (
  id              BIGSERIAL PRIMARY KEY,
  unique_link     VARCHAR(255) UNIQUE NOT NULL,
  expiration_date DATE NOT NULL,
  is_done         BOOLEAN DEFAULT FALSE  -- 발송 완료 여부
)

-- 캡슐 편지 (링크별 메시지)
capsule_entry (
  id           BIGSERIAL PRIMARY KEY,
  link_id      BIGINT REFERENCES capsule_link(id),
  name         VARCHAR(255) NOT NULL,
  email        VARCHAR(255) NOT NULL,
  body         TEXT NOT NULL,
  is_processed BOOLEAN DEFAULT FALSE  -- 이메일 발송 처리 여부
)
```

---

## 환경 설정

프로젝트 루트에 `.env` 파일을 생성하고 아래 값을 설정합니다.

```env
DB_URL=jdbc:postgresql://localhost:5432/dazimcapsule
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password

EMAIL_HOST=smtp.example.com
EMAIL_PORT=587
EMAIL_USERNAME=your_email@example.com
EMAIL_PASSWORD=your_email_password
```

---

## 실행 방법

```bash
# 빌드
./gradlew build

# 실행
./gradlew bootRun
```

API 문서는 서버 실행 후 `/swagger-ui.html` 에서 확인할 수 있습니다.

---

## API 요약

| Method | Endpoint | 설명 |
|--------|----------|------|
| `POST` | `/makeForm` | 새 캡슐 링크 생성 |
| `GET` | `/form/{link}` | 링크 유효성 확인 |
| `POST` | `/form/{link}` | 편지 제출 |
