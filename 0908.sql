-- #### LIKE

SELECT 고객이름, 나이, 등급, 적립금
FROM 고객
WHERE 고객이름 LIKE '김%';

SELECT * FROM 고객;

SELECT 고객이름, 등급, 직업
FROM 고객
WHERE 등급 LIKE '%i%';

SELECT 고객이름, 등급, 직업
FROM 고객
WHERE 고객아이디 LIKE '%a%';

SELECT 고객아이디, 고객이름, 등급, 직업
FROM 고객
WHERE (고객아이디 LIKE '%a%' AND 등급 LIKE '%i%') OR 고객이름 LIKE '김%';

SELECT 고객아이디, 나이, 등급, 직업, 적립금
FROM 고객
WHERE 고객아이디 LIKE '_____';

SELECT 고객아이디, 나이, 등급, 직업, 적립금
FROM 고객
WHERE 고객아이디 LIKE '_____' AND 적립금 >= 3000;

SELECT 고객이름, 직업, 등급
FROM 고객
WHERE 직업 LIKE '%사';

-- #### IS NULL

SELECT 고객이름, 나이
FROM 고객
WHERE 나이 IS NOT NULL;

-- 입력된 값이 없는 컬럼(필드)는 비교연산 결과가 false
SELECT 고객이름, 나이
FROM 고객
WHERE 나이 >= 25;

-- ### ORDER BY
-- NULL이 나오는 순서 ASC : 맨 뒤, DESC : 맨 처음

SELECT 고객이름, 등급, 나이
FROM 고객
ORDER BY 나이 DESC;

SELECT 고객이름, 나이, 등급, 직업
FROM 고객
WHERE 등급 = 'gold' OR 등급 = 'vip'
ORDER BY 등급 ASC;

SELECT *
FROM 주문
WHERE (주문제품 = 'p03' OR 주문제품 = 'p06') AND 배송지 LIKE '경기도%'
ORDER BY 수량 DESC;

SELECT * FROM 주문;

SELECT 주문고객, 주문제품, 수량, 주문일자
FROM 주문
WHERE 수량 >= 10
ORDER BY 주문제품 ASC, 수량 DESC;

SELECT 주문고객, 주문제품, 수량, 배송지
FROM 주문
WHERE (주문고객 LIKE 'a%' OR 주문고객 LIKE 'b%') AND 수량 >= 10
ORDER BY 주문고객 DESC, 주문제품 DESC;