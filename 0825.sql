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


