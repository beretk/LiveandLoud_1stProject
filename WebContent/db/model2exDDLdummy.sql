DROP TABLE FILEBOARD;
DROP SEQUENCE FILEBOARD_SEQ;
DROP TABLE MEMBER;
DROP TABLE NOTICE;
DROP SEQUENCE NOTICE_SEQ;
DROP TABLE PHOTO;
DROP SEQUENCE PHOTO_SEQ;
DROP TABLE EQUIPMENT;
DROP SEQUENCE EQUIPMENT_SEQ;
DROP TABLE ADMIN;

----------------------------------------------------------------------
--                    MEMBER    테이블 생성                      --
----------------------------------------------------------------------
CREATE TABLE MEMBER(
    mID VARCHAR2(30) PRIMARY KEY,
    mPW VARCHAR2(30) NOT NULL,
    mNAME VARCHAR2(30) NOT NULL,
    mEMAIL VARCHAR2(30) UNIQUE,
    mPHOTO VARCHAR2(30) NOT NULL, 
    mBIRTH DATE,
    mADDRESS VARCHAR2(300),
    mRDATE DATE DEFAULT SYSDATE NOT NULL    
);

-- DUMMY DATA
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('gayun','1','김가연','gayun@naver.com','gayun.jpg','1972/09/09','광주광역시');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('gico','1','지코','gico@naver.com','gico.jpg','1992/09/14','서울시');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('go','1','고소영','go@naver.com','go.jpg','1972/10/06','서울시');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('han','1','한지민','han@naver.com','han.jpg','1982/11/05','서울시');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('jang','1','장동건',null,'jang.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('jo','1','조현우',null,'jo.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('kang','1','강동원',null,'kang.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('kim','1','김태희',null,'kim.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('lee','1','이선빈',null,'lee.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('lim','1','임요한',null,'lim.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('park','1','박보검',null,'park.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('rain','1','비',null,'rain.jpg',null,null);
INSERT INTO MVC_MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('sgy','1','송가연',null,'songkayeon.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('son','1','손흥민',null,'son.jpg',null,null);
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MPHOTO, MBIRTH, MADDRESS)
    VALUES ('song','1','송중기',null,'song.jpg',null,null);
SELECT * FROM MEMBER ORDER BY mRDATE DESC;


----------------------------------------------------------------------
--                   FILEBOARD 테이블 생성                           --
----------------------------------------------------------------------
CREATE TABLE FILEBOARD(
    fID     NUMBER(6) PRIMARY KEY,         -- 글번호
    mID     VARCHAR2(30) REFERENCES MEMBER(MID) NOT NULL, -- 아이디
    fTITLE  VARCHAR2(100) NOT NULL,         -- 글제목
    fCONTENT VARCHAR2(4000),               -- 글본문
    fFILENAME VARCHAR2(100),
    fRDATE   DATE DEFAULT SYSDATE NOT NULL, -- 글 작성시점
    fHIT    NUMBER(6) DEFAULT 0,           -- 글 조회수
    fGROUP  NUMBER(6) NOT NULL,            -- 글그룹(원글의 경우 글번호로/답변글일경우 원글의 BGROUP로)
    fSTEP   NUMBER(2) NOT NULL,            -- 글그룹내 출력 순서(원글 0)
    fINDENT NUMBER(3) NOT NULL,            -- 글 LIST 출력시 글 제목 들여쓰기 정도(원글0)
    fIP     VARCHAR2(50) NOT NULL          -- 글 쓴 컴퓨터의 IP    
);

CREATE SEQUENCE FILEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;

-- dummy data (3개 이상 - 2개원글+1개답변글)
-- 더미데이터 원글쓰기 (글1)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'go','글1','content',null, FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 첫번째 글1
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 첫번째 답변글)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'gico','글1-1(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 두번째 답변글)
UPDATE FILEBOARD SET FSTEP = FSTEP+1 
    WHERE FGROUP = 1 AND FSTEP>0;
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'gico','글1-2(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;

-- 더미데이터 답변글쓰기(위의 "글1-2"에 대한 첫번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE FILEBOARD SET FSTEP = FSTEP+1 
    WHERE FGROUP = 1 AND FSTEP>1;
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'gayun','글1-1-1','content', null, 1, 2, 2, '192.168.10.151');
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;
-- 더미데이터 원글 쓰기(글4)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
    VALUES (FILEBOARD_SEQ.NEXTVAL, 'go','글4','content','a.docx', FILEBOARD_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 원글4 쓰기
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;
  
UPDATE MVC_BOARD SET BHIT=11 WHERE BID=3;

----------------------------------------------------------------------
--                   ADMIN TABLE    테이블 생성                      -- 
----------------------------------------------------------------------
CREATE TABLE ADMIN(
    aID VARCHAR2(30) PRIMARY KEY,
    aPW VARCHAR2(30) NOT NULL,
    aNAME VARCHAR2(30) NOT NULL
);
INSERT INTO ADMIN VALUES('admin', '1', '관리자');
COMMIT;
SELECT * FROM ADMIN;
----------------------------------------------------------------------
--                   NOTICE 테이블 생성                           --
----------------------------------------------------------------------
CREATE TABLE NOTICE(
    nID     NUMBER(6) PRIMARY KEY,         -- 글번호
    aID     VARCHAR2(30) REFERENCES ADMIN(aID) NOT NULL, -- 아이디
    nTITLE  VARCHAR2(100) NOT NULL,         -- 글제목
    nCONTENT VARCHAR2(4000),               -- 글본문
    nFILENAME VARCHAR2(100),
    nRDATE   DATE DEFAULT SYSDATE NOT NULL, -- 글 작성시점
    nHIT    NUMBER(6)DEFAULT 0,            -- 글 조회수
    nGROUP  NUMBER(6) NOT NULL,            -- 글그룹(원글의 경우 글번호로/답변글일경우 원글의 BGROUP로)
    nSTEP   NUMBER(2) NOT NULL,            -- 글그룹내 출력 순서(원글 0)
    nINDENT NUMBER(3) NOT NULL,            -- 글 LIST 출력시 글 제목 들여쓰기 정도(원글0)
    nIP    VARCHAR2(30) DEFAULT 0           -- 글 쓴 컴퓨터의 IP   
);

CREATE SEQUENCE NOTICE_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
SELECT * FROM NOTICE;

-- dummy data (3개 이상 - 2개원글+1개답변글)
-- 더미데이터 원글쓰기 (글1)
INSERT INTO NOTICE (NID, AID, NTITLE, NCONTENT, NFILENAME, NGROUP, NSTEP, NINDENT, NIP)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','글1','content',null, NOTICE_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 첫번째 글1
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 첫번째 답변글)
INSERT INTO NOTICE (NID, AID, NTITLE, NCONTENT, NFILENAME, NGROUP, NSTEP, NINDENT, NIP)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','글1-1(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 두번째 답변글)
UPDATE NOTICE SET NSTEP = NSTEP+1 
    WHERE NGROUP = 1 AND NSTEP>0;
INSERT INTO NOTICE (NID, AID, NTITLE, NCONTENT, NFILENAME, NGROUP, NSTEP, NINDENT, NIP)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','글1-2(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;

-- 더미데이터 답변글쓰기(위의 "글1-2"에 대한 첫번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE NOTICE SET NSTEP = NSTEP+1 
    WHERE NGROUP = 1 AND NSTEP>1;
INSERT INTO NOTICE (NID, AID, NTITLE, NCONTENT, NFILENAME, NGROUP, NSTEP, NINDENT, NIP)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','글1-1-1','content', null, 1, 2, 2, '192.168.10.151');
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;
-- 더미데이터 원글 쓰기(글4)
INSERT INTO NOTICE (NID, AID, NTITLE, NCONTENT, NFILENAME, NGROUP, NSTEP, NINDENT, NIP)
    VALUES (NOTICE_SEQ.NEXTVAL, 'admin','글4','content','a.docx', NOTICE_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 원글4 쓰기
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;
  
UPDATE NOTICE SET NHIT=11 WHERE NID=3;
COMMIT;

----------------------------------------------------------------------
--                   PHOTO 테이블 생성                           --
----------------------------------------------------------------------
CREATE TABLE PHOTO(
    pID     NUMBER(6) PRIMARY KEY,         -- 글번호
    aID     VARCHAR2(30) REFERENCES ADMIN(aID) NOT NULL, -- 아이디
    pTITLE  VARCHAR2(100) NOT NULL,         -- 글제목
    pCONTENT VARCHAR2(4000),               -- 글본문
    pFILENAME VARCHAR2(100),
    pRDATE   DATE DEFAULT SYSDATE NOT NULL, -- 글 작성시점
    pHIT    NUMBER(6) DEFAULT 0,           -- 글 조회수
    pGROUP  NUMBER(6) NOT NULL,            -- 글그룹(원글의 경우 글번호로/답변글일경우 원글의 BGROUP로)
    pSTEP   NUMBER(2) NOT NULL,            -- 글그룹내 출력 순서(원글 0)
    pINDENT NUMBER(3) NOT NULL,            -- 글 LIST 출력시 글 제목 들여쓰기 정도(원글0)
    pIP     VARCHAR2(50) NOT NULL          -- 글 쓴 컴퓨터의 IP    
);

CREATE SEQUENCE PHOTO_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;

-- dummy data (3개 이상 - 2개원글+1개답변글)
-- 더미데이터 원글쓰기 (글1)
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
    VALUES (PHOTO_SEQ.NEXTVAL, 'admin','글1','content',null, PHOTO_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 첫번째 글1
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 첫번째 답변글)
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
    VALUES (PHOTO_SEQ.NEXTVAL, 'admin','글1-1(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 두번째 답변글)
UPDATE PHOTO SET PSTEP = PSTEP+1 
    WHERE PGROUP = 1 AND PSTEP>0;
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
    VALUES (PHOTO_SEQ.NEXTVAL, 'admin','글1-2(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;

-- 더미데이터 답변글쓰기(위의 "글1-2"에 대한 첫번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE PHOTO SET PSTEP = PSTEP+1 
    WHERE PGROUP = 1 AND PSTEP>1;
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
    VALUES (PHOTO_SEQ.NEXTVAL, 'admin','글1-1-1','content', null, 1, 2, 2, '192.168.10.151');
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;
-- 더미데이터 원글 쓰기(글4)
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
    VALUES (PHOTO_SEQ.NEXTVAL, 'admin','글4','content','a.docx', PHOTO_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 원글4 쓰기
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;
  
UPDATE PHOTO SET PHIT=11 WHERE PID=3;
SELECT * FROM PHOTO;

----------------------------------------------------------------------
--                   EQUIPMENT 테이블 생성                           --
----------------------------------------------------------------------
CREATE TABLE EQUIPMENT(
    eID     NUMBER(6) PRIMARY KEY,         -- 글번호
    aID     VARCHAR2(30) REFERENCES ADMIN(aID) NOT NULL, -- 아이디
    eTITLE  VARCHAR2(100) NOT NULL,         -- 글제목
    eCONTENT VARCHAR2(4000),               -- 글본문
    eFILENAME VARCHAR2(100),
    eRDATE   DATE DEFAULT SYSDATE NOT NULL, -- 글 작성시점
    eHIT    NUMBER(6) DEFAULT 0,           -- 글 조회수
    eGROUP  NUMBER(6) NOT NULL,            -- 글그룹(원글의 경우 글번호로/답변글일경우 원글의 BGROUP로)
    eSTEP   NUMBER(2) NOT NULL,            -- 글그룹내 출력 순서(원글 0)
    eINDENT NUMBER(3) NOT NULL,            -- 글 LIST 출력시 글 제목 들여쓰기 정도(원글0)
    eIP     VARCHAR2(50) NOT NULL          -- 글 쓴 컴퓨터의 IP    
);

CREATE SEQUENCE EQUIPMENT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;

-- dummy data (3개 이상 - 2개원글+1개답변글)
-- 더미데이터 원글쓰기 (글1)
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
    VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','글1','content',null, EQUIPMENT_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 첫번째 글1
SELECT * FROM EQUIPMENT ORDER BY EGROUP DESC, ESTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 첫번째 답변글)
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
    VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','글1-1(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM EQUIPMENT ORDER BY EGROUP DESC, ESTEP;

-- 더미데이터 답변글쓰기 (위의 "글1"에 대한 두번째 답변글)
UPDATE EQUIPMENT SET ESTEP = ESTEP+1 
    WHERE EGROUP = 1 AND ESTEP>0;
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
    VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','글1-2(글1의 첫답변)','content', null, 1, 1, 1, '192.168.20.31');
SELECT * FROM EQUIPMENT ORDER BY EGROUP DESC, ESTEP;

-- 더미데이터 답변글쓰기(위의 "글1-2"에 대한 첫번째 답변글)
--    답변글 추가전 STEP a 수행
UPDATE EQUIPMENT SET ESTEP = ESTEP+1 
    WHERE EGROUP = 1 AND ESTEP>1;
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
    VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','글1-1-1','content', null, 1, 2, 2, '192.168.10.151');
SELECT * FROM EQUIPMENT ORDER BY EGROUP DESC, ESTEP;
-- 더미데이터 원글 쓰기(글4)
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
    VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','글4','content','a.docx', EQUIPMENT_SEQ.CURRVAL, 0, 0, '192.168.20.31'); -- 원글4 쓰기
SELECT * FROM EQUIPMENT ORDER BY EGROUP DESC, ESTEP;
  
UPDATE EQUIPMENT SET EHIT=11 WHERE EID=3;
SELECT * FROM EQUIPMENT;

------------------------------------------------
--                 확인용                      -- 
------------------------------------------------
SELECT * FROM MEMBER ORDER BY mRDATE DESC;
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;
SELECT * FROM ADMIN;
SELECT * FROM NOTICE;
SELECT * FROM PHOTO;
SELECT * FROM EQUIPMENT;


