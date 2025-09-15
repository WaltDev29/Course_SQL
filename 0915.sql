-- #### COUNT
SELECT COUNT(DISTINCT 제조업체) AS "제조업체 수" FROM 제품;

SELECT COUNT(DISTINCT 주문고객) AS "주문고객 수" FROM 주문;

-- ### GROUP BY
SELECT * FROM 주문;

SELECT 주문제품, SUM(수량) AS 총주문수량
FROM 주문
GROUP BY 주문제품
ORDER BY 주문제품 ASC;

SELECT 주문고객, ROUND(AVG(수량),1) AS 수량평균
FROM 주문
GROUP BY 주문고객
ORDER BY 주문고객 ASC;

SELECT 등급, ROUND(AVG(적립금),2)
FROM 고객
GROUP BY 등급
ORDER BY 등급;

SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가
FROM 제품
GROUP BY 제조업체;

SELECT 직업, ROUND(AVG(나이)) AS 평균나이, MIN(적립금) AS "최저 적립금"
FROM 고객
GROUP BY 직업;

-- ### HAVING
SELECT 제조업체, COUNT(*) 제품수, MAX(단가) AS 최고가
FROM 제품
GROUP BY 제조업체
HAVING COUNT(*) >= 3;

SELECT 제조업체, COUNT(*) AS 제품수, MAX(단가) AS 최고가
FROM 제품
GROUP BY 제조업체
HAVING MAX(단가) >= 4000;

SELECT 제조업체, COUNT(*) 제품수, MAX(단가) 최고가
FROM 제품
GROUP BY 제조업체
HAVING COUNT(*) <= 2 AND MAX(단가) >= 4000;

SELECT 등급, COUNT(*) AS 고객수, ROUND(AVG(적립금),2)
FROM 고객
GROUP BY 등급
HAVING ROUND(AVG(적립금),2) >= 1000;

SELECT 주문고객, 주문제품, SUM(수량) 총주문수량
FROM 주문
GROUP BY 주문고객, 주문제품
ORDER BY 주문고객;

-- #### BETWEEN
SELECT  주문고객, 주문제품, SUM(수량) AS 총주문수량
FROM 주문
GROUP BY 주문고객, 주문제품
HAVING SUM(수량) >= 30 AND 주문제품 BETWEEN 'p01' AND 'p04';

-- ## JOIN
-- 자연조인 (Natural Join)
SELECT o.주문번호, o.주문고객, p.제품명, p.단가, p.제조업체
FROM 주문 o, 제품 p
WHERE o.주문제품 = p.제품번호;

SELECT p.제품명
FROM 주문 o, 제품 p
WHERE o.주문제품 = p.제품번호 AND 주문고객 = 'banana';

SELECT c.고객이름, c.나이, c.직업, p.제품명, p.단가, o.주문일자, o.배송지
FROM 주문 o, 고객 c, 제품 p
WHERE o.주문고객 = c.고객아이디 AND o.주문제품 = p.제품번호 AND o.주문고객 = 'apple';

SELECT 나이, 제품번호, 제품명, 주문일자
FROM 주문 o, 제품 p, 고객 c
WHERE o.주문제품 = p.제품번호 AND o.주문고객 = c.고객아이디
    AND c.나이 >= 30;

SELECT * FROM 주문;
SELECT * FROM 고객;