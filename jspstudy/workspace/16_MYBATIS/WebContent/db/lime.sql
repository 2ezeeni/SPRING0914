DROP TABLE LIME;
CREATE TABLE LIME
(
	NO NUMBER PRIMARY KEY,
	WRITER VARCHAR2(100),
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000),
	POSTDATE TIMESTAMP WITH LOCAL TIME ZONE
);
DROP SEQUENCE LIME_SEQ;
CREATE SEQUENCE LIME_SEQ
START WITH 1
INCREMENT BY 1
MAXVALUE 9999999
NOCYCLE
NOCACHE;