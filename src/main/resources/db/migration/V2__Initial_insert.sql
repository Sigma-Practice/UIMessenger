INSERT INTO user(id,login, email, password, enabled, fullname, phone, info, isActivated, isMute)
VALUES (DEFAULT ,'User', 'user@messenger.com', '$2a$10$aK7DjH5ACI1ii4Zvf.eYi.BGoMvl42DcjZxYJVggzv2AVYX2C2APe', DEFAULT , 'Test user', '123456789', 'some info about user', TRUE, TRUE);
INSERT INTO user(id,login, email, password, enabled, fullname, phone, info, isActivated, isMute)
VALUES (DEFAULT ,'Admin', 'admin@messenger.com', '$2a$10$IW2ewiIGHeCTxO4O6iQuD.fV0JizlvxrZi8Bl3Emaml090GuXCTTG', DEFAULT , 'Test admin', '987654321', 'some info about admin', TRUE, TRUE);
INSERT INTO user_roles (login, ROLE)
VALUES ('User', 'ROLE_USER');
INSERT INTO user_roles (login, ROLE)
VALUES ('Admin', 'ROLE_ADMIN');
INSERT INTO user_roles (login, ROLE)
VALUES ('Admin', 'ROLE_USER');
