-- 테이블 삭제 (의존성 역순으로 삭제하여 외래 키 제약 충돌 방지)
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS board_comment;
DROP TABLE IF EXISTS channel_board;
DROP TABLE IF EXISTS video_comment;
DROP TABLE IF EXISTS video;
DROP TABLE IF EXISTS subscribe;
DROP TABLE IF EXISTS image;
DROP TABLE IF EXISTS member;
SET foreign_key_checks = 1;

-- Member 테이블 생성
CREATE TABLE IF NOT EXISTS member (
                                      idx INT AUTO_INCREMENT PRIMARY KEY,
                                      email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    content TEXT,  -- Added content column, nullable
    member_role VARCHAR(50) NOT NULL,
    sum_score BIGINT NOT NULL DEFAULT 0,
    sum_score_member BIGINT NOT NULL DEFAULT 0,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );

-- ChannelBoard 테이블 생성
CREATE TABLE IF NOT EXISTS channel_board
(
    idx
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    title
    VARCHAR
(
    255
) NOT NULL,
    contents TEXT NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    member_idx INT NOT NULL,
    FOREIGN KEY
(
    member_idx
) REFERENCES member
(
    idx
)
    );

-- BoardComment 테이블 생성
CREATE TABLE IF NOT EXISTS board_comment
(
    idx
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    content
    TEXT
    NOT
    NULL,
    is_deleted
    BOOLEAN
    NOT
    NULL
    DEFAULT
    FALSE,
    created_at
    TIMESTAMP
    NOT
    NULL
    DEFAULT
    CURRENT_TIMESTAMP,
    updated_at
    TIMESTAMP
    NULL
    DEFAULT
    NULL
    ON
    UPDATE
    CURRENT_TIMESTAMP,
    board_idx
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    board_idx
) REFERENCES channel_board
(
    idx
)
    );

-- Video 테이블 생성
CREATE TABLE IF NOT EXISTS video
(
    idx INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    is_visibility BOOLEAN NOT NULL DEFAULT TRUE,
    original_filename VARCHAR(255) NOT NULL,
    original_path VARCHAR(500) NOT NULL,
    original_size BIGINT NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    saved_path VARCHAR(500),
    saved_size BIGINT,
    video_status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );
ALTER TABLE video ADD FULLTEXT INDEX ft_title_description (title, description);

-- VideoComment 테이블 생성
CREATE TABLE IF NOT EXISTS video_comment
(
    idx
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    content
    TEXT
    NOT
    NULL,
    is_deleted
    BOOLEAN
    NOT
    NULL
    DEFAULT
    FALSE,
    created_at
    TIMESTAMP
    NOT
    NULL
    DEFAULT
    CURRENT_TIMESTAMP,
    updated_at
    TIMESTAMP
    NULL
    DEFAULT
    NULL
    ON
    UPDATE
    CURRENT_TIMESTAMP,
    video_idx
    INT
    NOT
    NULL,
    FOREIGN
    KEY
(
    video_idx
) REFERENCES video
(
    idx
)
    );

-- Subscribe 테이블 생성
CREATE TABLE IF NOT EXISTS subscribe
(
    idx
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    channel_idx
    INT
    NOT
    NULL,
    subscriber_idx
    INT
    NOT
    NULL,
    vote_score
    BOOLEAN
    NOT
    NULL
    DEFAULT
    FALSE,
    created_at
    TIMESTAMP
    NOT
    NULL
    DEFAULT
    CURRENT_TIMESTAMP,
    updated_at
    TIMESTAMP
    NULL
    DEFAULT
    NULL
    ON
    UPDATE
    CURRENT_TIMESTAMP,
    FOREIGN
    KEY
(
    channel_idx
) REFERENCES member
(
    idx
),
    FOREIGN KEY
(
    subscriber_idx
) REFERENCES member
(
    idx
),
    INDEX idx_channel
(
    channel_idx
),
    INDEX idx_subscriber
(
    subscriber_idx
)
    );

-- Image 테이블 생성
CREATE TABLE IF NOT EXISTS image
(
    idx
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    original_name
    VARCHAR
(
    255
) NOT NULL,
    image_name VARCHAR
(
    255
) NOT NULL,
    image_url VARCHAR
(
    255
) NOT NULL,
    image_path VARCHAR
(
    255
) NOT NULL,
    file_size BIGINT NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
    );