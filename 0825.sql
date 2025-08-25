-- CREATE
-- 고객 테이블 생성
create table 고객(
    고객아이디 VARCHAR(20) NOT NULL PRIMARY KEY,
    고객이름 VARCHAR(10) NOT NULL,
    나이 INT,
    등급 VARCHAR(10) NOT NULL,
    직업 VARCHAR(20),
    적립금 INT DEFAULT 0
);

-- 제품 테이블 생성
create table 제품 (
    제품번호 CHAR(3) NOT NULL,
    제품명 VARCHAR(20),
    재고량 INT,
    단가 INT,
    제조업체 VARCHAR(20),
    PRIMARY KEY (제품번호),
    CHECK(재고량 >=0 AND 재고량 <= 10000)
);

-- 주문 테이블 생성
CREATE TABLE 주문 (
    주문번호 VARCHAR(3) NOT NULL,
    주문고객 VARCHAR(20),
    주문제품 CHAR(3),
    수량 INT,
    배송지 VARCHAR(30),
    주문일자 DATE,
    PRIMARY KEY(주문번호),
    FOREIGN KEY(주문고객) REFERENCES 고객(고객아이디),
    FOREIGN KEY(주문제품) REFERENCES 제품(제품번호)
);

-- 배송업체 테이블 생성
CREATE TABLE 배송업체 (
    업체번호 CHAR(3) NOT NULL PRIMARY KEY,
    업체명 VARCHAR(20),
    주소 VARCHAR(100),
    전화번호 VARCHAR(20)
);


-- ALTER TABLE문
-- ADD
ALTER TABLE 고객
ADD 가입날짜 DATE;

-- DROP
ALTER TABLE 고객
DROP COLUMN 가입날짜;

-- ADD CONSTRAINT
ALTER TABLE 고객
ADD CONSTRAINT CHK_AGE CHECK(나이 >= 20);

-- DROP CONSTRAINT
ALTER TABLE 고객
DROP CONSTRAINT CHK_AGE;

--DROP TABLE
DROP TABLE 배송업체;

-- INSERT
INSERT INTO 고객 VALUES('apple','정소화',20,'gold','학생',1000);
-- 한 번에 삽입
INSERT ALL 
INTO 고객 VALUES('banana','김선우',25,'vip','간호사',2500)
INTO 고객 VALUES('carrot','고명석',28,'gold','교사',4500)
INTO 고객 VALUES('orange','김용욱',22,'silver','학생',0)
INTO 고객 VALUES('melon','성원용',35,'gold','회사원',5000)
INTO 고객 VALUES('peach','오형준',NULL,'silver','의사',300)
INTO 고객 VALUES('pear','채광주',31,'silver','회사원',500)
SELECT * FROM dual; -- 쿼리 실행을 위한 더미 테이블