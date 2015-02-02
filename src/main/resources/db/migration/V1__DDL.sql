CREATE TABLE user(id INTEGER generated by default as identity PRIMARY KEY ,login VARCHAR (45) NOT NULL UNIQUE ,email VARCHAR (45) NOT NULL UNIQUE ,  password VARCHAR (60) not NULL ,
   enabled TINYINT DEFAULT 1 NOT NULL,fullname VARCHAR (120) NOT NULL, phone VARCHAR (20),
   info VARCHAR(2048), isActivated BOOLEAN DEFAULT FALSE NOT NULL, isMute BOOLEAN DEFAULT FALSE NOT NULL);

CREATE TABLE user_roles(
  user_role_id INTEGER generated by default as identity PRIMARY KEY,
  login VARCHAR(45) NOT NULL,
  ROLE VARCHAR(45) NOT NULL,
  UNIQUE (ROLE,login),
  FOREIGN KEY (login) REFERENCES user (login));

CREATE TABLE topic(id INTEGER generated by default as identity PRIMARY KEY , name VARCHAR (360) NOT NULL UNIQUE );
CREATE TABLE chat(id INTEGER generated by default as identity PRIMARY KEY , topicId INTEGER NOT NULL REFERENCES topic(id) ON DELETE CASCADE,name VARCHAR (360) NOT NULL UNIQUE );
CREATE TABLE message(id INTEGER generated by default as identity PRIMARY KEY , chatId INTEGER NOT NULL REFERENCES chat(id) ON DELETE CASCADE,
  userId INTEGER NOT NULL REFERENCES user(id) ON DELETE NO ACTION, text VARCHAR (2048) NOT NULL, sent DATE NOT NULL);

