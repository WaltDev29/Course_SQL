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



-- DELETE
DELETE FROM 한빛제품
WHERE 단가 >= 1000 AND 단가 <= 2500;

DELETE FROM 고객
WHERE 고객이름 = '김키위';



-- VIEW
-- CREATE
CREATE VIEW 우수고객 (고객아이디, 고객이름, 나이, 등급)
AS SELECT 고객아이디, 고객이름, 나이, 등급
    FROM 고객
    WHERE 등급 = 'vip'
    WITH CHECK OPTION;
    
SELECT * FROM 우수고객;

select *
FROM 우수고객
WHERE 나이 >= 20;

CREATE VIEW 업체별제품수
AS SELECT 제조업체, COUNT(*) AS 제품수
    FROM 제품
    GROUP BY 제조업체
WITH CHECK OPTION;
    
SELECT * FROM 업체별제품수;



-- 변경 가능한 뷰
CREATE VIEW 제품2
AS SELECT 제품번호, 재고량, 제조업체
    FROM 제품
WITH CHECK OPTION;

INSERT INTO 제품2
VALUES ('p08', 1000, '신선식품');

SELECT * FROM 제품2;
-- VIEW에서 수정한 내용은 원본 테이블에도 적용됨
SELECT * FROM 제품;



-- 변경 불가능한 뷰
-- 1. PK를 포함하지 않은 VIEW
CREATE VIEW 제품1
AS SELECT 제품명, 재고량, 제조업체
    FROM 제품
WITH CHECK OPTION;
    
SELECT * FROM 제품1;

INSERT INTO 제품1
VALUES ('호호비스킷', 500, '호호제과');



-- DROP VIEW
DROP VIEW 제품1;

SELECT * FROM 한빛제품;
SELECT * FROM 고객;
SELECT * FROM 제품;
SELECT * FROM 주문;