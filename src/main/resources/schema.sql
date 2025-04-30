-- 1 DDL

-- siatpost.BOARD_TBL definition

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

-- siatpost.BOOKMARK_TBL definition

CREATE TABLE `BOOKMARK_TBL` (
  `bookmark_idx` int(11) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `post_idx` bigint(20) NOT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY (`bookmark_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- siatpost.COMMENT_TBL definition

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

-- siatpost.LIKE_TBL definition

CREATE TABLE `LIKE_TBL` (
  `like_idx` bigint(20) NOT NULL,
  `user_idx` int(11) NOT NULL,
  `post_idx` bigint(20) NOT NULL,
  `reg_date` datetime NOT NULL,
  PRIMARY KEY (`like_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- siatpost.MEMBER_TBL definition

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



-- siatpost.POST_TBL definition

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

INSERT INTO `MEMBER_TBL` (
    `user_id`,
    `user_password`,
    `user_name`,
    `user_email`,
    `user_nickname`,
    `grade`
) VALUES (
    'admin123',
    'securepass',
    '관리자',
    'admin@example.com',
    '최고관리자',
    'ADMIN'
);

INSERT INTO BOARD_TBL
(board_idx, board_name, board_description, board_slug, reg_date, update_date, is_delete)
VALUES(0, '', NULL, '', current_timestamp(), current_timestamp(), 0);

INSERT INTO POST_TBL
(post_idx, board_idx, user_idx, post_author, post_title, post_content, hit, is_secret, post_password, reg_date, update_date, is_delete)
VALUES(0, 0, NULL, '익명', '', NULL, 0, 0, NULL, current_timestamp(), current_timestamp(), 0);




