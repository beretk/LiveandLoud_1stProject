--------------------------------------------------------------
---------------  MemberDao에 들어갈 query ---------------------
--------------------------------------------------------------
-- (1) 회원mid 중복체크
SELECT * FROM MEMBER WHERE mID='aaa';
SELECT * FROM MEMBER;

-- (2) 회원email 중복체크
SELECT COUNT(*) CNT FROM MEMBER WHERE mEMAIL='aaa@naver.com';

-- (3) 회원가입
INSERT INTO MEMBER (mID, mPw, mName, mEmail, mPhoto, mBirth, mAddress)
    VALUES ('bae','1','배두나','bae@naver.com', 'NOIMG.JPG', '1980/12/25','서울시');
    
-- (4) 로그인
SELECT * FROM MEMBER WHERE mID='aaa' and mPW='1';

-- (5) mid로 dto가져오기(로그인 성공시 session에 setAttribute하기 위함)
SELECT * FROM MEMBER WHERE mID='aaa';

-- (6) 회원정보 수정(특정 mid의 정보 수정)
UPDATE MEMBER 
    SET mPW = '1',
        mNAME = '신길동',
        mEMAIL = 'shin@hong.com',
        mBIRTH = '1999-01-01',
        mADDRESS = '경기도'
    WHERE mID = 'aaa';
    
-- (7) 회원리스트(top-N구문)
SELECT * FROM MEMBER ORDER BY mRDATE DESC;
SELECT ROWNUM RN, A.*
    FROM(SELECT * FROM MEMBER ORDER BY mRDATE DESC) A; -- TOP-N 구문의 INLINE VIEW에 들어갈 QUERY
SELECT *
    FROM(SELECT ROWNUM RN, A.*
        FROM(SELECT * FROM MEMBER ORDER BY mRDATE DESC) A)
    WHERE RN BETWEEN 4 AND 6;
    
-- (8) 회원수
SELECT COUNT (*) CNT FROM MEMBER;

-- (9) 회원탈퇴
COMMIT;
DELETE MEMBER WHERE mID='aaa';
ROLLBACK;

SELECT * FROM MEMBER ORDER BY mRDATE DESC; 


--------------------------------------------------------------
-----------------  BoardDao에 들어갈 query --------------------
--------------------------------------------------------------

-- (1) 글목록(startRow~endRow)
SELECT F.*, MNAME FROM FILEBOARD F, MEMBER M
  WHERE F.MID=M.MID 
  ORDER BY FGROUP DESC, FSTEP; -- 출력 기준
SELECT * FROM
  (SELECT ROWNUM RN, A.* FROM (SELECT F.*, MNAME FROM FILEBOARD F, MEMBER M
                              WHERE F.MID=M.MID 
                              ORDER BY FGROUP DESC, FSTEP) A)
  WHERE RN BETWEEN 2 AND 4; -- dao에 쓸 query
    
-- (2) 글갯수
SELECT COUNT(*) CNT FROM FILEBOARD;

-- (3) 글쓰기(원글쓰기)
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
  VALUES (FILEBOARD_SEQ.NEXTVAL, 'son','토트넘','난 공격수', 'a.docx', 
    FILEBOARD_SEQ.CURRVAL, 0,0, '192.168.0.31');
    
-- (4) hit 1회 올리기
UPDATE FILEBOARD SET FHIT = FHIT + 1 WHERE FID=1;

-- (5) 글번호(fid)로 글전체 내용(BoardDto) 가져오기
SELECT F.*, MNAME
  FROM FILEBOARD F, MEMBER M WHERE F.MID=M.MID AND FID=1;
  
-- (6) 글 수정하기(fid, ftitle, fcontent, ffilename, frdate(SYSDATE), fip 수정)
UPDATE FILEBOARD SET FTITLE = '바뀐제목',
                    FCONTENT = '바뀐본문',
                    fFILENAME = NULL,
                    FIP = '192.168.151.10',
                    FRDATE = SYSDATE
            WHERE FID = 2;
            
-- (7) 글 삭제하기
COMMIT;
-- 글 삭제시 해당 글 하나 삭제하기 (삭제하려는 글의 FID필요. 3번글 삭제)
DELETE FROM FILEBOARD WHERE FID=11;
ROLLBACK;
-- 글 삭제시 답변글까지 삭제하는 로직(지우려는 3번글의 삭제하려는 글의 FGROUP=1, FSTEP=1, FINDENT=1 필요)
SELECT * FROM FILEBOARD ORDER BY FGROUP DESC, FSTEP;
DELETE FROM FILEBOARD WHERE FGROUP = 1 AND (FSTEP>=1 AND 
    FSTEP<(SELECT NVL(MIN(FSTEP),9999) 
          FROM FILEBOARD WHERE FGROUP=1 AND FSTEP>1 AND FINDENT<=1));
UPDATE FILEBOARD SET fSTEP = fSTEP-2 WHERE fGROUP=1 AND fSTEP>2;-- 생략 가능(2은 위의 DELETE문 수행결과) : fstep 재조정
COMMIT;

-- (8) 답변글 쓰기 전 단계(원글의 fgroup과 같고, 원글의 fstep보다 크면 fstep을 하나 증가하기)
UPDATE FILEBOARD SET FSTEP = FSTEP + 1 WHERE FGROUP=5 AND FSTEP>0;

-- (9) 답변글 쓰기
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
  VALUES (FILEBOARD_SEQ.NEXTVAL, 'son','박지성 아님', '잘한다', null, 
    5, 1, 1, '194.161.13.11'); -- 5번글의 답변글
COMMIT;

-- (10) 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 글 모두 삭제하기
DELETE FROM FILEBOARD WHERE MID='son';
ROLLBACK;

--------------------------------------------------------------
-----------------  AdminDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) admin 로그인
SELECT * FROM ADMIN WHERE aID='admin' and aPW='1';

-- (2) 로그인 후 세션에 넣을 용도 : admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE aID='admin';

--------------------------------------------------------------
-----------------  NoticeDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT * FROM NOTICE;
SELECT A.*, ANAME FROM NOTICE N, ADMIN A
  WHERE N.AID=A.AID ; -- 출력 기준
SELECT * FROM
  (SELECT ROWNUM RN, A.* FROM (SELECT N.*, ANAME FROM NOTICE N, ADMIN A
        WHERE N.AID=A.AID ORDER BY NGROUP DESC, NSTEP) A)
  WHERE RN BETWEEN 2 AND 4; -- dao에 쓸 query
   
-- (2) 글갯수
SELECT COUNT(*) CNT FROM NOTICE;

-- (3) 글쓰기(원글쓰기) 
INSERT INTO FILEBOARD (FID, MID, FTITLE, FCONTENT, FFILENAME, FGROUP, FSTEP, FINDENT, FIP)
  VALUES (FILEBOARD_SEQ.NEXTVAL, 'admin','토트넘','난 공격수', 'a.docx', 
    FILEBOARD_SEQ.CURRVAL, 0,0, '192.168.0.31');
    
-- (4) hit 1회 올리기
UPDATE NOTICE SET NHIT = NHIT + 1 WHERE NID=1;

-- (5) 글번호(nid)로 글전체 내용(NoticeDto) 가져오기
SELECT N.*, ANAME
  FROM NOTICE N, ADMIN A WHERE N.AID=A.AID AND NID=1;  
  
-- (6) 글 수정하기(nid, ntitle, ncontent, nfilename, nrdate(SYSDATE), nip 수정)
UPDATE NOTICE SET NTITLE = '바뀐제목',
                    NCONTENT = '바뀐본문',
                    NFILENAME = NULL,
                    NIP = '192.168.151.10',
                    NRDATE = SYSDATE
            WHERE NID = 2;
            
-- (7) 글 삭제하기
COMMIT;
DELETE FROM NOTICE WHERE NID=5;
ROLLBACK;
-- 글 삭제시 답변글까지 삭제하는 로직(지우려는 3번글의 삭제하려는 글의 NGROUP=1, NSTEP=1, NINDENT=1 필요)
SELECT * FROM NOTICE ORDER BY NGROUP DESC, NSTEP;
DELETE FROM NOTICE WHERE NGROUP = 1 AND (NSTEP>=1 AND 
    NSTEP<(SELECT NVL(MIN(NSTEP),9999) 
          FROM NOTICE WHERE NGROUP=1 AND NSTEP>1 AND NINDENT<=1));
ROLLBACK;
UPDATE NOTICE SET NSTEP = NSTEP-2 WHERE NGROUP=1 AND NSTEP>2;-- 생략 가능(2은 위의 DELETE문 수행결과) : Nstep 재조정


COMMIT;
--------------------------------------------------------------
-----------------  PhotoDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT * FROM PHOTO;
SELECT P.*, ANAME FROM PHOTO P, ADMIN A
  WHERE P.AID=A.AID ; -- 출력 기준
SELECT * FROM
  (SELECT ROWNUM RN, A.* FROM (SELECT P.*, ANAME FROM PHOTO P, ADMIN A
        WHERE P.AID=A.AID ORDER BY PGROUP DESC, PSTEP) A)
  WHERE RN BETWEEN 2 AND 4; -- dao에 쓸 query
   
-- (2) 글갯수
SELECT COUNT(*) CNT FROM PHOTO;

-- (3) 글쓰기(원글쓰기) 
INSERT INTO PHOTO (PID, AID, PTITLE, PCONTENT, PFILENAME, PGROUP, PSTEP, PINDENT, PIP)
  VALUES (PHOTOSEQ.NEXTVAL, 'admin','토트넘','난 공격수', 'a.docx', 
    PHOTO_SEQ.CURRVAL, 0,0, '192.168.0.31');
    
-- (4) hit 1회 올리기
UPDATE PHOTO SET PHIT = PHIT + 1 WHERE PID=1;

-- (5) 글번호(nid)로 글전체 내용(PhotoDto) 가져오기
SELECT P.*, ANAME
  FROM PHOTO P, ADMIN A WHERE P.AID=A.AID AND PID=1;  
  
-- (6) 글 수정하기(pid, ptitle, pcontent, pfilename, prdate(SYSDATE), pip 수정)
UPDATE PHOTO SET PTITLE = '바뀐제목',
                    PCONTENT = '바뀐본문',
                    PFILENAME = NULL,
                    PIP = '192.168.151.10',
                    PRDATE = SYSDATE
            WHERE PID = 2;
            
-- (7) 글 삭제하기
COMMIT;
DELETE FROM PHOTO WHERE PID=5;
ROLLBACK;
-- 글 삭제시 답변글까지 삭제하는 로직(지우려는 3번글의 삭제하려는 글의 PGROUP=1, PSTEP=1, PINDENT=1 필요)
SELECT * FROM PHOTO ORDER BY PGROUP DESC, PSTEP;
DELETE FROM PHOTO WHERE PGROUP = 1 AND (PSTEP>=1 AND 
    PSTEP<(SELECT NVL(MIN(PSTEP),9999) 
          FROM PHOTO WHERE PGROUP=1 AND PSTEP>1 AND PINDENT<=1));
ROLLBACK;
UPDATE PHOTO SET PSTEP = PSTEP-2 WHERE PGROUP=1 AND PSTEP>2;-- 생략 가능(2은 위의 DELETE문 수행결과) : Pstep 재조정


COMMIT;
--------------------------------------------------------------
-----------------  EquipmentDao에 들어갈 query ----------------
--------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT * FROM EQUIPMENT;
SELECT E.*, ANAME FROM EQUIPMENT E, ADMIN A
  WHERE E.AID=A.AID ; -- 출력 기준
SELECT * FROM
  (SELECT ROWNUM RN, A.* FROM (SELECT E.*, ANAME FROM EQUIPMENT E, ADMIN A
        WHERE E.AID=A.AID ORDER BY EGROUP DESC, ESTEP) A)
  WHERE RN BETWEEN 2 AND 4; -- dao에 쓸 query
   
-- (2) 글갯수
SELECT COUNT(*) CNT FROM EQUIPMENT;

-- (3) 글쓰기(원글쓰기) 
INSERT INTO EQUIPMENT (EID, AID, ETITLE, ECONTENT, EFILENAME, EGROUP, ESTEP, EINDENT, EIP)
  VALUES (EQUIPMENT_SEQ.NEXTVAL, 'admin','토트넘','난 공격수', 'a.docx', 
    EQUIPMENT_SEQ.CURRVAL, 0,0, '192.168.0.31');
    
-- (4) hit 1회 올리기
UPDATE EQUIPMENT SET eHIT = eHIT + 1 WHERE eID=1;

-- (5) 글번호(nid)로 글전체 내용(EquipmentDto) 가져오기
SELECT E.*, ANAME
  FROM EQUIPMENT E, ADMIN A WHERE E.AID=A.AID AND EID=1;  
  
-- (6) 글 수정하기(eid, etitle, econtent, efilename, erdate(SYSDATE), eip 수정)
UPDATE EQUIPMENT SET ETITLE = '바뀐제목',
                    ECONTENT = '바뀐본문',
                    EFILENAME = NULL,
                    EIP = '192.168.151.10',
                    ERDATE = SYSDATE
            WHERE EID = 2;
            
-- (7) 글 삭제하기
COMMIT;
DELETE FROM EQUIPMENT WHERE EID=5;
ROLLBACK;

COMMIT;



