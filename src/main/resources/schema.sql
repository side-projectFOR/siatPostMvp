-- 1 DDL

-- BOARD_TBL definition

CREATE TABLE `BOARD_TBL` (
  `board_idx` int(11) NOT NULL AUTO_INCREMENT,
  `board_name` varchar(100) NOT NULL,
  `board_description` text DEFAULT NULL,
  `board_slug` varchar(20) NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`board_idx`),
  UNIQUE KEY `board_slag` (`board_slug`),
  UNIQUE KEY `board_slug` (`board_slug`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- BOOKMARK_TBL definition

CREATE TABLE `BOOKMARK_TBL` (
  `bookmark_idx` int(11) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `post_idx` bigint(20) NOT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY (`bookmark_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- COMMENT_TBL definition

CREATE TABLE `COMMENT_TBL` (
  `comment_idx` bigint(20) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `post_idx` bigint(20) DEFAULT NULL,
  `comment_parent_idx` bigint(20) DEFAULT NULL,
  `comment_content` varchar(4000) NOT NULL,
  `comment_author` varchar(100) NOT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`comment_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- LIKE_TBL definition

CREATE TABLE `LIKE_TBL` (
  `like_idx` bigint(20) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `post_idx` bigint(20) NOT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY (`like_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- MEMBER_TBL definition

CREATE TABLE `MEMBER_TBL` (
  `user_idx` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `user_password` varchar(512) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_email` varchar(150) DEFAULT NULL,
  `user_nickname` varchar(100) NOT NULL DEFAULT 'TEST_NICKNAME',
  `grade` enum('USER','ADMIN') NOT NULL DEFAULT 'USER',
  `user_point` bigint(20) NOT NULL DEFAULT 0,
  `user_thumbnail_file_url` varchar(500) DEFAULT NULL,
  `profile_description` varchar(1000) DEFAULT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;



-- POST_TBL definition

CREATE TABLE `POST_TBL` (
  `post_idx` bigint(20) NOT NULL AUTO_INCREMENT,
  `board_idx` int(11) NOT NULL,
  `user_idx` int(11) DEFAULT NULL,
  `post_author` varchar(100) NOT NULL DEFAULT '익명',
  `post_title` varchar(200) NOT NULL,
  `post_content` mediumtext DEFAULT NULL,
  `hit` int(11) NOT NULL DEFAULT 0,
  `is_secret` tinyint(4) NOT NULL DEFAULT 0,
  `post_password` varchar(512) DEFAULT NULL,
  `reg_date` datetime NOT NULL DEFAULT current_timestamp(),
  `update_date` datetime NOT NULL DEFAULT current_timestamp(),
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`post_idx`),
  KEY `FK_board_TO_post_1` (`board_idx`),
  KEY `user_idx` (`user_idx`),
  CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (`board_idx`) REFERENCES `board_tbl` (`board_idx`),
  CONSTRAINT `post_tbl_ibfk_1` FOREIGN KEY (`user_idx`) REFERENCES `member_tbl` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- 2 DML

INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(15, 'johndoe', 'P@ssw0rd123', 'John Doe', 'john.doe@example.com', 'JD', 'USER', 0, 'https://example.com/images/profile/johndoe.jpg', '안녕하세요! 저는 John입니다. 백엔드 개발자로 일하고 있으며 Java와 Spring을 주로 사용합니다.', '2025-04-10 15:42:30.000', '2025-04-10 15:42:30.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(16, 'johndoe', 'P@ssw0rd123', 'John Doe', 'john.doe@example.com', 'JD', 'USER', 0, 'https://example.com/images/profile/johndoe.jpg', '안녕하세요! 저는 John입니다. 백엔드 개발자로 일하고 있으며 Java와 Spring을 주로 사용합니다.', '2025-04-10 15:44:54.000', '2025-04-10 15:44:54.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(17, 'johndoe', 'P@ssw0rd123', 'John Doe', 'john.doe@example.com', 'JD', 'USER', 0, 'https://example.com/images/profile/johndoe.jpg', '안녕하세요! 저는 John입니다. 백엔드 개발자로 일하고 있으며 Java와 Spring을 주로 사용합니다.', '2025-04-10 15:50:20.000', '2025-04-10 15:50:20.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(18, 'johndoe1', 'P@ssw0rd123', 'John Doe', 'john.doe@example.com', 'JD', 'USER', 0, 'https://example.com/images/profile/johndoe.jpg', '안녕하세요! 저는 John입니다. 백엔드 개발자로 일하고 있으며 Java와 Spring을 주로 사용합니다.', '2025-04-11 16:33:00.000', '2025-04-11 16:33:00.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(19, 'test', 'P@ssw0rd123', 'John Doe', 'john.doe@example.com', 'JD', 'USER', 0, 'https://example.com/images/profile/johndoe.jpg', '안녕하세요! 저는 John입니다. 백엔드 개발자로 일하고 있으며 Java와 Spring을 주로 사용합니다.', '2025-04-11 16:52:37.000', '2025-04-11 16:52:37.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(20, 'string', 'string', 'string', 'string', 'string', 'USER', 0, 'string', 'string', '2025-04-22 16:48:15.000', '2025-04-22 16:48:15.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(21, 'string', 'string', 'string', 'string', 'string', 'USER', 0, 'string', 'string', '2025-04-22 16:49:02.000', '2025-04-22 16:49:02.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(22, 'siat2025', 'P@ssw0rd!', '홍길동', 'user@example.com', '길동이', 'USER', 0, 'https://cdn.example.com/images/profile.jpg', '안녕하세요, 홍길동입니다.', '2025-04-23 09:12:35.000', '2025-04-23 09:12:35.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(23, 'siat2025', 'P@ssw0rd!', '홍길동', 'user@example.com', '길동이', 'USER', 0, 'https://cdn.example.com/images/profile.jpg', '안녕하세요, 홍길동입니다.', '2025-04-25 16:09:25.000', '2025-04-25 16:09:25.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(24, 'siat2025', 'P@ssw0rd!', '홍길동', 'user@example.com', '길동이', 'USER', 0, 'https://cdn.example.com/images/profile.jpg', '안녕하세요, 홍길동입니다.', '2025-04-25 16:46:41.000', '2025-04-25 16:46:41.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(25, 'bokdol2012', 'P@ssw0rd!', '복돌이', 'user@example.com', '길동이', 'USER', 0, 'https://cdn.example.com/images/profile.jpg', '안녕하세요, 홍길동입니다.', '2025-04-25 16:50:25.000', '2025-04-25 16:50:25.000', 0);
INSERT INTO MEMBER_TBL
(user_idx, user_id, user_password, user_name, user_email, user_nickname, grade, user_point, user_thumbnail_file_url, profile_description, reg_date, update_date, is_delete)
VALUES(26, 'siat2025', 'P@ssw0rd!', '홍길동', 'user@example.com', '길동이', 'USER', 0, 'https://cdn.example.com/images/profile.jpg', '안녕하세요, 홍길동입니다.', '2025-04-25 18:03:59.000', '2025-04-25 18:03:59.000', 0);

INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(0, '', NULL, '', current_timestamp(), current_timestamp(), 0);
INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(1, '자유게시판', '자유자유자유', 'free', '2025-04-11 07:48:28.000', '2025-04-11 07:48:28.000', 0);
INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(2, '게시게시판', '게시판 설명수정', 'string', '2025-04-22 07:58:14.000', '2025-04-24 01:23:23.000', 0);
INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(5, 'string', 'string', 'test', '2025-04-22 08:08:48.000', '2025-04-22 08:08:48.000', 0);
INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(6, 'string123', 'string123', 'string123', '2025-04-22 08:23:12.000', '2025-04-22 08:23:12.000', 1);
INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(7, '코드리뷰', '코드리뷰게시판', 'code-review', '2025-04-24 00:53:12.000', '2025-04-24 00:53:12.000', 1);




INSERT INTO POST_TBL
(post_idx, board_idx, user_idx, post_author, post_title, post_content, hit, is_secret, post_password, reg_date, update_date, is_delete)
VALUES(1, 1, 15, 'JohnDoe', 'Sample Post Title', 'This is the content of the sample post.', 7, 0, NULL, '2025-04-15 07:47:45.000', '2025-04-15 07:48:35.000', 0);
INSERT INTO POST_TBL
(post_idx, board_idx, user_idx, post_author, post_title, post_content, hit, is_secret, post_password, reg_date, update_date, is_delete)
VALUES(2, 1, 15, 'JohnDoe', 'Sample Post Title', 'This is the content of the sample post.', 1, 0, NULL, '2025-04-15 07:48:35.000', '2025-04-15 07:48:35.000', 0);
INSERT INTO POST_TBL
(post_idx, board_idx, user_idx, post_author, post_title, post_content, hit, is_secret, post_password, reg_date, update_date, is_delete)
VALUES(3, 1, 15, 'string', 'string', 'string', 0, 1, 'string', '2025-04-22 08:32:07.000', '2025-04-15 07:48:35.000', 0);
INSERT INTO POST_TBL
(post_idx, board_idx, user_idx, post_author, post_title, post_content, hit, is_secret, post_password, reg_date, update_date, is_delete)
VALUES(4, 1, 15, '유저1', 'test', 'test', 2, 1, 'test', '2025-04-24 00:56:30.000', '2025-04-15 07:48:35.000', 0);



