DROP DATABASE IF EXISTS community;
CREATE DATABASE community;
USE community;

DROP TABLE IF EXISTS `like`;
DROP TABLE IF EXISTS `file`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `post`;
DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `member`;

-- 1. 회원 테이블 (member)
CREATE TABLE `member` (
   `id`          VARCHAR(15)   PRIMARY KEY NOT NULL,
   `password`    VARCHAR(255)  NOT NULL,
   `email`       VARCHAR(255)  NOT NULL,
   `role`        VARCHAR(10)   DEFAULT 'USER' NOT NULL
);

-- 2. 게시판 테이블 (board)
CREATE TABLE `board` (
   `id`          INT           PRIMARY KEY AUTO_INCREMENT NOT NULL,
   `name`        VARCHAR(50)   UNIQUE NOT NULL
);

-- 3. 게시글 테이블 (post)
CREATE TABLE `post` (
   `id`          INT           PRIMARY KEY AUTO_INCREMENT NOT NULL,
   `title`       VARCHAR(255)  NOT NULL,
   `content`     LONGTEXT      NOT NULL,
   `created_at`  DATETIME      DEFAULT CURRENT_TIMESTAMP NOT NULL,
   `view_count`  INT           DEFAULT 0 NOT NULL,
   `up_count`    INT           DEFAULT 0 NOT NULL,
   `down_count`  INT           DEFAULT 0 NOT NULL,
   `is_deleted`  CHAR(1)       DEFAULT 'N' NOT NULL,
   `member_id`   VARCHAR(15)   NOT NULL,
   `board_id`    INT           NOT NULL
);

-- 4. 좋아요/싫어요 테이블 (post_like)
-- 'like'는 SQL 예약어이므로 post_like로 수정
CREATE TABLE `post_like` (
   `id`          INT           PRIMARY KEY AUTO_INCREMENT NOT NULL,
   `state`       INT           NOT NULL, -- 1: 좋아요, -1: 싫어요 등
   `member_id`   VARCHAR(15)   NOT NULL,
   `post_id`     INT           NOT NULL
);

-- 5. 첨부파일 테이블 (file)
CREATE TABLE `file` (
   `id`             INT           PRIMARY KEY AUTO_INCREMENT NOT NULL,
   `original_name`  VARCHAR(255)  NOT NULL,
   `saved_name`     VARCHAR(255)  NOT NULL,
   `post_id`        INT           NOT NULL
);

-- 6. 댓글 테이블 (comment)
CREATE TABLE `comment` (
   `id`             INT           PRIMARY KEY AUTO_INCREMENT NOT NULL, -- AUTO_INCREMENT 추가
   `content`        VARCHAR(500)  NOT NULL,
   `created_at`     DATETIME      DEFAULT CURRENT_TIMESTAMP NOT NULL,
   `origin_id`      INT           , -- 대댓글 구현용 원댓글 번호
   `is_deleted`     CHAR(1)       DEFAULT 'N' NOT NULL,
   `member_id`      VARCHAR(15)   NOT NULL,
   `post_id`        INT           NOT NULL
);

-- Foreign Key 제약조건 설정

ALTER TABLE `post` ADD CONSTRAINT `FK_member_TO_post` FOREIGN KEY (
   `member_id`
)
REFERENCES `member` (
   `id`
);

ALTER TABLE `post` ADD CONSTRAINT `FK_board_TO_post` FOREIGN KEY (
   `board_id`
)
REFERENCES `board` (
   `id`
);

ALTER TABLE `post_like` ADD CONSTRAINT `FK_member_TO_post_like` FOREIGN KEY (
   `member_id`
)
REFERENCES `member` (
   `id`
);

ALTER TABLE `post_like` ADD CONSTRAINT `FK_post_TO_post_like` FOREIGN KEY (
   `post_id`
)
REFERENCES `post` (
   `id`
);

ALTER TABLE `file` ADD CONSTRAINT `FK_post_TO_file` FOREIGN KEY (
   `post_id`
)
REFERENCES `post` (
   `id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_member_TO_comment` FOREIGN KEY (
   `member_id`
)
REFERENCES `member` (
   `id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment` FOREIGN KEY (
   `post_id`
)
REFERENCES `post` (
   `id`
);