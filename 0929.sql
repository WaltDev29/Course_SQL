-- INSERT

-- 속성 리스트를 생략하여 추가
INSERT INTO 고객
VALUES ('strawberry','최유경',30,'vip','공무원',100);

-- 속성 리스트를 작성하여 추가
INSERT INTO 고객 (고객아이디, 적립금, 나이, 등급, 고객이름, 직업)
VALUES ('kiwi', 500, 23, 'silver', '김키위', '학생');

-- 특정 속성들만 입력
INSERT INTO 고객
VALUES ('grapes', '허선희', NULL, 'vip', '회사원', 1200);

INSERT INTO 고객 (고객아이디,고객이름,등급,직업,적립금)
VALUES ('moonberry','문세진','gold','학생',500);

-- 서브 쿼리 활용 INSERT

CREATE TABLE 한빛제품 (
 제품명 VARCHAR(20) NOT NULL PRIMARY KEY,
 재고량 NUMBER,
 단가 NUMBER
);

INSERT INTO 한빛제품
    SELECT 제품명, 재고량, 단가
    FROM 제품
    WHERE 제조업체 = '한빛제과';



-- UPDATE

UPDATE 제품
SET 제품명 = '통큰파이'
WHERE 제품번호 = 'p03';

UPDATE 제품
SET 제품명 = '신나라면', 단가 = 2000
WHERE 제품번호 = 'p05';


UPDATE 제품
SET 단가 = 단가*1.1;


-- 서브 쿼리 활용 UPDATE 
UPDATE 주문
SET 수량 = 5
WHERE 주문고객 IN (SELECT 고객아이디
                    FROM 고객
                    WHERE 고객이름 = '정소화');

SELECT * FROM 한빛제품;
SELECT * FROM 고객;
SELECT * FROM 제품;
SELECT * FROM 주문;