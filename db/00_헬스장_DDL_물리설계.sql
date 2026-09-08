drop database if exists health;

create database health;

use health;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id`	int	primary key auto_increment,
	`name`	varchar(20)	not null,
	`phone`	varchar(13)	not null unique,
	`reg_date`	datetime not null default current_timestamp
);

DROP TABLE IF EXISTS `trainer`;

CREATE TABLE `trainer` (
	`id`	int	primary key auto_increment,
	`name`	varchar(20)	not null,
	`major`	varchar(10)	NULL,
	`history`	int	not null default 0,
	`leader_id`	int	NULL
);

DROP TABLE IF EXISTS `locker`;

CREATE TABLE `locker` (
	`id`	int	primary key auto_increment,
	`user_id`	int	NULL
);

DROP TABLE IF EXISTS `program`;

CREATE TABLE `program` (
	`id`	int	primary key auto_increment,
	`title`	varchar(20)	not null,
	`personnel`	int	not null,
	`fee`	int	not null default 0,
	`leader_id`	int	NULL
);

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
	`id`	int	primary key auto_increment,
	`app_date`	datetime not null default current_timestamp,
	`state`	varchar(10)	not null default '결제완료',
	`program_id`	int	NOT NULL,
	`user_id`	int	NOT NULL
);

DROP TABLE IF EXISTS `attendance`;

CREATE TABLE `attendance` (
	`id`	int	primary key auto_increment,
	`date`	datetime not null default current_timestamp,
	`state`	char(1)	not null,
	`program_id`	int	NOT NULL,
	`user_id`	int	NOT NULL
);



ALTER TABLE `trainer` ADD CONSTRAINT `FK_trainer_TO_trainer_1` FOREIGN KEY (
	`leader_id`
)
REFERENCES `trainer` (
	`id`
);

ALTER TABLE `locker` ADD CONSTRAINT `FK_user_TO_locker_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `program` ADD CONSTRAINT `FK_trainer_TO_program_1` FOREIGN KEY (
	`leader_id`
)
REFERENCES `trainer` (
	`id`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_program_TO_course_1` FOREIGN KEY (
	`program_id`
)
REFERENCES `program` (
	`id`
);

ALTER TABLE `course` ADD CONSTRAINT `FK_user_TO_course_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `attendance` ADD CONSTRAINT `FK_program_TO_attendance_1` FOREIGN KEY (
	`program_id`
)
REFERENCES `program` (
	`id`
);

ALTER TABLE `attendance` ADD CONSTRAINT `FK_user_TO_attendance_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`id`
);


