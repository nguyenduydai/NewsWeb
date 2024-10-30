use hoclaptrinhjavaweb;

CREATE TABLE role(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createdate TIMESTAMP NULL,
    modifiedate TIMESTAMP NULL,
	createby VARCHAR(255) NULL,
    modifieby VARCHAR(255) NULL
);

CREATE TABLE user(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    username VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    fullname VARCHAR(150) NULL,
    status int NULL,
    roleid bigint NOT NULL,
    createdate TIMESTAMP NULL,
    modifiedate TIMESTAMP NULL,
	createby VARCHAR(255) NULL,
    modifieby VARCHAR(255) NULL
);
ALTER TABLE user ADD roleid bigint NOT NULL;
ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);

CREATE TABLE news(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    title VARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255) NOT NULL,
    shortdescription TEXT NULL,
    content TEXT NULL,
    categoryid bigint NOT NULL,
    createdate TIMESTAMP NULL,
    modifiedate TIMESTAMP NULL,
	createby VARCHAR(255) NULL,
    modifieby VARCHAR(255) NULL
);

CREATE TABLE category(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    createdate TIMESTAMP NULL,
    modifiedate TIMESTAMP NULL,
	createby VARCHAR(255) NULL,
    modifieby VARCHAR(255) NULL
);
ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id);

CREATE TABLE comment(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    content TEXT NOT NULL,
	userid bigint NOT NULL,
	newsid bigint NOT NULL,
    createdate TIMESTAMP NULL,
    modifiedate TIMESTAMP NULL,
	createby VARCHAR(255) NULL,
    modifieby VARCHAR(255) NULL
);
ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (userid) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (newsid) REFERENCES news(id);

