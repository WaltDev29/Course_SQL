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