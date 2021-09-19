
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `udaily` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `udaily`;

-- --------------------------------------------------------



CREATE TABLE `comments` (
  `comments_id` varchar(10) NOT NULL,
  `professor_id` varchar(10) NOT NULL,
  `username` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  `comment` varchar(255) NOT NULL,
  `rate` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;



INSERT INTO `comments` (`comments_id`, `professor_id`, `username`, `time`, `comment`, `rate`) VALUES
('83bo1uCSnC', 'UOFS000001', 'maki', '2020-04-04 20:09:40', 'Good', 4),
('rhdnBBAU3M', 'UOFS000001', 'qwe', '2020-04-04 20:23:11', 'OK', 3),
('VxjHMA8q1L', 'UOFS000001', 'qwe', '2020-04-05 02:34:43', 'Good', 4),
('9vzOULUzow', 'UOFS000000', 'qwe', '2020-04-05 03:15:17', 'good', 3),
('QxbjQTIpLo', 'UOFS000000', 'qwe', '2020-04-05 04:54:57', 'good', 3),
('Lx8PWdupTh', 'UOFS000002', 'qwe', '2020-04-06 18:20:05', 'good', 3),
('gQjGmfVOmn', 'UOFS000001', 'qwe', '2020-04-05 17:04:05', 'good', 3),
('0DBPSwDlJB', 'UOFS000002', 'qwe', '2020-04-05 16:59:09', 'Good', 3),
('pX0cOciVlq', 'UOFS000002', 'qwe', '2020-04-05 16:34:24', 'good', 3);

-- --------------------------------------------------------


CREATE TABLE `discussion` (
  `discussion_id` varchar(20) NOT NULL,
  `reply_id` varchar(50) DEFAULT NULL,
  `poster` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  `subject_id` varchar(10) NOT NULL,
  `type` varchar(50) NOT NULL,
  `topic` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `discussion` (`discussion_id`, `reply_id`, `poster`, `time`, `subject_id`, `type`, `topic`, `content`) VALUES
('InYCCdC9CCTSzrurW2RO', 'wpNXpzlE7KytdbxeYWfj', 'abcde', '2020-04-05 22:43:28', 'CMPT-370', 'Mark', 'Milestone', '5/5'),
('HSiWQ66y3lC2NYeTNjkY', 'wpNXpzlE7KytdbxeYWfj', 'qwe', '2020-04-06 18:20:51', 'CMPT-370', 'Reply', 'Milestone', '123'),
('iW65agv0gH41Nxe2BUTv', NULL, 'qwe', '2020-04-05 19:22:16', 'CMPT-370', 'Consult', 'Milestone', 'Is it OK?'),
('wpNXpzlE7KytdbxeYWfj', 'iW65agv0gH41Nxe2BUTv', 'qwe', '2020-04-05 19:23:29', 'CMPT-370', 'Reply', 'Milestone', 'OK!');

-- --------------------------------------------------------


CREATE TABLE `discussion_tag` (
  `tag` varchar(20) NOT NULL,
  `discussion_id` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `discussion_tag` (`tag`, `discussion_id`) VALUES
('cmpt370', 'HSiWQ66y3lC2NYeTNjkY'),
('cmpt370', 'InYCCdC9CCTSzrurW2RO'),
('cmpt370', 'iW65agv0gH41Nxe2BUTv'),
('cmpt370', 'wpNXpzlE7KytdbxeYWfj'),
('Milestone', 'HSiWQ66y3lC2NYeTNjkY'),
('Milestone', 'InYCCdC9CCTSzrurW2RO'),
('Milestone', 'iW65agv0gH41Nxe2BUTv'),
('Milestone', 'wpNXpzlE7KytdbxeYWfj');

-- --------------------------------------------------------


CREATE TABLE `main_subject` (
  `main_id` varchar(10) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `main_subject` (`main_id`, `name`) VALUES
('CMPT', 'Computer Science'),
('MATH', 'Mathematic');

-- --------------------------------------------------------


CREATE TABLE `professor` (
  `professor_id` varchar(10) NOT NULL,
  `Firstname` varchar(100) NOT NULL,
  `Lastname` varchar(100) NOT NULL,
  `University` varchar(100) NOT NULL,
  `username` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `professor` (`professor_id`, `Firstname`, `Lastname`, `University`, `username`) VALUES
('UOFS000000', 'Kevin', 'Schneider', 'University of Saskachetwan', ''),
('UOFS000001', 'Michael', 'Horsch', 'University of Saskatchewan', 'abcde'),
('UOFS000002', 'Zadia', 'Codabux', 'University of Saskatchewan', '');

-- --------------------------------------------------------


CREATE TABLE `sell` (
  `item_id` varchar(50) NOT NULL,
  `OwnerID` varchar(10) NOT NULL DEFAULT '',
  `description` varchar(255) NOT NULL,
  `ContactInfo` varchar(50) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL,
  `subject_id` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------


CREATE TABLE `subject` (
  `subject_id` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `main_id` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `subject` (`subject_id`, `name`, `main_id`) VALUES
('CMPT-370', 'Intermediate Software Engineering', 'CMPT'),
('CMPT-423', 'Machine Learning', 'CMPT'),
('MATH-363', 'Abstract Algebra', 'MATH');

-- --------------------------------------------------------


CREATE TABLE `subject_relation` (
  `relation_id` varchar(20) NOT NULL,
  `professor_id` varchar(50) NOT NULL,
  `subject_id` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

INSERT INTO `subject_relation` (`relation_id`, `professor_id`, `subject_id`) VALUES
('UOFSCMPT370000000000', 'UOFS000000', 'CMPT-370'),
('UOFSCMPT370000000001', 'UOFS000001', 'CMPT-423'),
('UOFSCMPT370000000002', 'UOFS000002', 'CMPT-370');

-- --------------------------------------------------------


CREATE TABLE `textbook` (
  `textbook_id` varchar(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `subject_id` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `textbook` (`textbook_id`, `name`, `subject_id`) VALUES
('CMPT423001', 'Artificial Intelligence: Modern Approach', 'CMPT-423');

-- --------------------------------------------------------


CREATE TABLE `user` (
  `Username` varchar(10) NOT NULL DEFAULT '',
  `Password` varchar(255) NOT NULL DEFAULT '',
  `Firstname` varchar(100) DEFAULT '',
  `Lastname` varchar(100) DEFAULT '',
  `Email` varchar(100) DEFAULT '',
  `University` varchar(255) DEFAULT '',
  `Identity` varchar(10) DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `user` (`Username`, `Password`, `Firstname`, `Lastname`, `Email`, `University`, `Identity`) VALUES
('qwe', '202cb962ac59075b964b07152d234b70', '', '', '', '', ''),
('abc', 'e10adc3949ba59abbe56e057f20f883e', '', '', '', '', ''),
('abcde', '202cb962ac59075b964b07152d234b70', '', '', '', '', 'Professor'),
('maki', '202cb962ac59075b964b07152d234b70', 'Erxun', 'Zhang', 'erz678@usask.ca', 'University of Saskatchewan', '');

-- --------------------------------------------------------


CREATE TABLE `user_subject` (
  `relation_id` varchar(20) NOT NULL,
  `username` varchar(10) NOT NULL,
  `subject_id` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


ALTER TABLE `comments`
  ADD PRIMARY KEY (`comments_id`);

ALTER TABLE `discussion`
  ADD PRIMARY KEY (`discussion_id`);

ALTER TABLE `discussion_tag`
  ADD PRIMARY KEY (`tag`,`discussion_id`);

ALTER TABLE `main_subject`
  ADD PRIMARY KEY (`main_id`);

ALTER TABLE `professor`
  ADD PRIMARY KEY (`professor_id`);

ALTER TABLE `sell`
  ADD PRIMARY KEY (`item_id`);

ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`);

ALTER TABLE `subject_relation`
  ADD PRIMARY KEY (`relation_id`);

ALTER TABLE `textbook`
  ADD PRIMARY KEY (`textbook_id`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
