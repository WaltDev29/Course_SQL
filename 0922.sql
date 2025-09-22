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
