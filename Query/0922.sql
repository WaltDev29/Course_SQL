SELECT p.제품명
FROM 고객 c,주문 o,제품 p
WHERE c.고객아이디 = o.주문고객 AND o.주문제품 = p.제품번호
    AND c.고객이름 = '고명석';

-- ### NATURAL JOIN
SELECT o.주문제품,o.주문일자
FROM 고객 c, 주문 o
WHERE c.고객아이디 = o.주문고객  AND c.나이 >= 30;

-- ### INNER JOIN
SELECT o.주문제품, o.주문일자
FROM 고객 c INNER JOIN 주문 o ON o.주문고객 = c.고객아이디
WHERE c.나이 >= 30;


-- ### OUTER JOIN
-- LEFT JOIN
SELECT c.고객이름, o.주문제품, o.주문일자
FROM 고객 c LEFT OUTER JOIN 주문 o ON c.고객아이디 = o.주문고객;

-- RIGHT JOIN
SELECT c.고객이름, o.주문제품, o.주문일자
FROM 주문 o RIGHT OUTER JOIN 고객 c ON c.고객아이디 = o.주문고객;



-- ## SUB QUERY

-- ### 반환값이 하나
SELECT 제품명, 단가
FROM 제품
WHERE 제조업체 = (SELECT 제조업체
                    FROM 제품
                    WHERE 제품명 = '달콤비스킷');

SELECT 주문고객, 주문제품, 수량
FROM 주문
WHERE 주문제품 = (SELECT 제품번호
                    FROM 제품
                    WHERE 제품명 = '쿵떡파이');

SELECT 고객이름, 적립금
FROM 고객
WHERE 적립금 = (SELECT MAX(적립금)
        FROM 고객);        

SELECT 고객이름, 적립금
FROM 고객
WHERE 적립금 = (SELECT MIN(적립금) FROM 고객);

-- ### 반환값이 여러개
-- #### IN
SELECT 제품명, 제조업체
FROM 제품
WHERE 제품번호 IN (SELECT 주문제품
                    FROM 주문
                    WHERE 주문고객 = 'banana');

SELECT c.고객아이디, c.나이, c.적립금, p.제품명, p.단가
FROM 고객 c, 주문 o, 제품 p
WHERE c.고객아이디 = o.주문고객 AND o.주문제품 = p.제품번호
AND 고객이름 IN (SELECT 고객이름
                    FROM 고객
                    WHERE 고객이름 LIKE '김%');

-- #### NOT IN
SELECT 제품명, 제조업체
FROM 제품
WHERE 제품번호 NOT IN (SELECT 주문제품
                        FROM 주문
                        WHERE 주문고객 = 'banana');
                        

-- #### ALL
SELECT 제품명, 단가, 제조업체
FROM 제품
WHERE 단가 > ALL (SELECT 단가
                    FROM 제품
                    WHERE 제조업체 = '대한식품');

SELECT 고객이름
FROM 고객
WHERE 고객아이디 IN (SELECT 주문고객
                    FROM 주문
                    WHERE 주문일자 = '2022/03/15');
                    
-- #### EXISTS
SELECT 고객이름
FROM 고객
WHERE EXISTS (SELECT *
                FROM 주문
                WHERE 주문일자 = '2022/01/01'
                    AND 주문.주문고객 = 고객.고객아이디);

-- #### NOT EXISTS
SELECT 고객이름
FROM 고객
WHERE NOT EXISTS (SELECT *
                    FROM 주문
                    WHERE 주문일자 = '2022/01/01'
                        AND 주문.주문고객 = 고객.고객아이디);

select * FROM 고객;
select * FROM 주문;
select * FROM 제품;