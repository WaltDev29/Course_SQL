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
