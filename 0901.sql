-- 제품테이블에 데이터 행들을 추가
insert all
into 제품 values ('p01','그냥만두',5000,4500,'대한식품')
into 제품 values ('p02','매운쫄면',2500,5500,'민국푸드')
into 제품 values ('p03','쿵떡파이',3600,2600,'한빛제과')
into 제품 values ('p04','맛난초콜릿',1250,2500,'한빛제과')
into 제품 values ('p05','얼큰라면',2200,1200,'대한식품')
into 제품 values ('p06','통통우동',1000,1550,'민국푸드')
into 제품 values ('p07','달콤비스킷',1650,1500,'한빛제과')
select * from dual;

-- 주문테이블에 데이터 행들을 추가
INSERT ALL
INTO 주문 VALUES('o01','apple','p03',10,'서울시 마포구','2022-01-01')
INTO 주문 VALUES('o02','melon','p01',5,'인천시 계양구','2022-01-10')
INTO 주문 VALUES('o03','banana','p06',45,'경기도 부천시','2022-01-11')
INTO 주문 VALUES('o04','carrot','p02',8,'부산시 금정구','2022-02-01')
INTO 주문 VALUES('o05','melon','p06',36,'경기도 용인시','2022-02-20')
INTO 주문 VALUES('o06','banana','p01',19,'충청북도 보은군','2022-03-02')
INTO 주문 VALUES('o07','apple','p03',22,'서울시 영등포구','2022-03-15')
INTO 주문 VALUES('o08','pear','p02',50,'강원도 춘천시','2022-04-10')
INTO 주문 VALUES('o09','banana','p04',15,'전라남도 목포시','2022-04-11')
INTO 주문 VALUES('o10','carrot','p03',20,'경기도 안양시','2022-05-22')
SELECT * FROM dual;

-- FK에 없는 값 삽입 시도
-- insert into 주문 values('o11','blue','p03',20,'경기도 안양시','2022-05-22');


-- # DML

-- ## SELECT (조회)
-- ### ALL
SELECT ALL 주문고객 FROM 주문;

-- ### DISTINCT
SELECT DISTINCT 주문고객 FROM 주문;

-- ### 선택 컬럼 조회
SELECT 고객아이디, 고객이름, 나이 FROM 고객;

-- ### 모든 컬럼 조회
SELECT * FROM 고객;
SELECT 고객아이디, 고객이름, 나이, 등급, 직업, 적립금 FROM 고객;

-- ### AS
SELECT 주문고객 AS 고객아이디, 주문제품 AS 제품번호, 배송지, 주문일자
FROM 주문;

-- ### 공백으로도 가능
SELECT 주문고객 고객아이디, 주문제품 제품번호, 배송지, 주문일자
FROM 주문;


-- ### 산술연산
SELECT 제품명, 단가+500 AS "조정 단가"
FROM 제품;

-- ### WHERE
SELECT 제품명, 재고량, 단가
FROM 제품
WHERE 제조업체 = '한빛제과';

SELECT 고객이름, 나이, 직업
FROM 고객
WHERE 직업 = '회사원';

--- #### 비교연산
SELECT 주문제품, 수량, 주문일자
FROM 주문
WHERE 주문고객 = 'apple' AND 수량 >= 15;

SELECT 고객아이디, 고객이름, 나이, 직업
FROM 고객
WHERE 직업 = '회사원' AND 나이 < 33;

SELECT 주문제품, 수량, 주문일자, 주문고객
FROM 주문
WHERE 주문고객 = 'apple' OR 수량 >= 15;

SELECT 고객이름, 등급, 직업
FROM 고객
WHERE 등급 = 'gold' OR 직업 = '의사';

SELECT 제품명, 단가, 제조업체
FROM 제품
WHERE 단가 >= 2000 AND 단가 <= 3000;

SELECT 나이, 등급, 직업
FROM 고객
WHERE (나이 >= 20 AND 나이 <= 30) AND
(등급 = 'gold' OR 등급 = 'silver') AND 직업 = '학생';