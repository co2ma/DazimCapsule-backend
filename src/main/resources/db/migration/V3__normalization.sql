DROP TABLE Capsule;

-- 1. 링크 관리 테이블
CREATE TABLE capsule_link
(
    id              BIGSERIAL    NOT NULL,
    unique_link     VARCHAR(255) NOT NULL,
    expiration_date DATE         NOT NULL,
    CONSTRAINT pk_capsule_link PRIMARY KEY (id),
    CONSTRAINT uq_capsule_link UNIQUE (unique_link)
);

-- 2. 캡슐 내용 테이블
CREATE TABLE capsule_entry
(
    id           BIGSERIAL    NOT NULL,
    link_id      BIGINT       NOT NULL,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    body         TEXT         NOT NULL,  -- VARCHAR(255) → TEXT 로 변경
    is_processed BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_capsule_entry PRIMARY KEY (id),
    CONSTRAINT fk_capsule_entry_link FOREIGN KEY (link_id) REFERENCES capsule_link (id) ON DELETE CASCADE
);

-- 3. 성능 최적화를 위한 인덱스 추가
-- 배치가 "특정 일자(expiration_date)에 만료되는 모든 데이터"를 찾기 위한 인덱스
CREATE INDEX idx_capsule_link_expiration_date ON capsule_link (expiration_date);
-- 4. 미처리 항목 조회 다시 할 때 용도
CREATE INDEX idx_capsule_entry_is_processed ON capsule_entry (is_processed);
