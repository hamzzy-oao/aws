 /-+# 물리설계
# ERD CLOUD: https://www.erdcloud.com/d/tZTPB8D7m7jQNErw5
DROP database IF exists SHOPPINGMALL;
create database SHOPPINGMALL;
USE SHOPPINGMALL;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id`	varchar(13)	NOT NULL,
	`pw`	varchar(255) NOT NULL,
	`email`	varchar(50)	NOT NULL unique,
	`phone`	varchar(13)	NOT NULL unique,
	`state`	varchar(5)	NOT NULL default '이용',
	`authority`	varchar(5) NOT NULL default 'USER',
	`login_count`	int	NOT NULL default 0
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`code`	char(3)	NOT NULL,
	`name`	varchar(10) NOT	NULL
);

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
	`code`	char(6)	NOT NULL,
	`name`	varchar(100) NOT NULL,
	`content`	text NOT NULL,
	`price`	int	NOT NULL default 0,
	`thumbnail`	varchar(255)	NULL,
	`amount`	int	NOT NULL default 0,
	`ca_code`	char(3)	NOT NULL
);

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
	`id`	varchar(13)	NOT NULL,
	`code`	char(6)	NOT NULL ,
	`amount`	int	NOT NULL,
	`date`	datetime NOT NULL default current_timestamp,
	`state`	varchar(30)	NOT NULL default '결제완료'
);

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
	`num`	int	primary KEY auto_increment,
	`amount`	int	NOT NULL,
	`id`	varchar(13)	NOT NULL,
	`code`	char(6)	NOT NULL
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`id`
);

ALTER TABLE `category` ADD CONSTRAINT `PK_CATEGORY` PRIMARY KEY (
	`code`
);

ALTER TABLE `product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `PK_BUY` PRIMARY KEY (
	`id`,
	`code`
);


ALTER TABLE `product` ADD CONSTRAINT `FK_category_TO_product_1` FOREIGN KEY (
	`ca_code`
)
REFERENCES `category` (
	`code`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_user_TO_buy_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_product_TO_buy_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_user_TO_cart_1` FOREIGN KEY (
	`id`
)
REFERENCES `user` (
	`id`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_product_TO_cart_1` FOREIGN KEY (
	`code`
)
REFERENCES `product` (
	`code`
);

