CREATE TABLE meals (
    meal_id INT NOT NULL,
    served_date DATE NOT NULL,
    meal_type VARCHAR2(10) NOT NULL,
    PRIMARY KEY(meal_id),
    UNIQUE(served_date, meal_type),
    CONSTRAINT check_type CHECK(meal_type in ('조식', '중식', '석식'))
);


CREATE TABLE foods (
    food_id INT NOT NULL,
    name VARCHAR2(30) NOT NULL,
    PRIMARY KEY(food_id),
    UNIQUE(name)
);


CREATE TABLE meal_foods (
    meal_id INT NOT NULL,
    food_id INT NOT NULL,
    PRIMARY KEY(meal_id, food_id),
    FOREIGN KEY(meal_id) REFERENCES meals(meal_id) ON DELETE CASCADE,
    FOREIGN KEY(food_id) REFERENCES foods(food_id) ON DELETE CASCADE
);


CREATE TABLE students (
    student_id VARCHAR2(10) NOT NULL,
    name VARCHAR2(20) NOT NULL,
    gender VARCHAR2(1),
    birth_date DATE,
    PRIMARY KEY(student_id),
    CONSTRAINT check_gender CHECK(gender IN ('F', 'M') OR gender IS NULL)
);

CREATE TABLE accounts (
    student_id VARCHAR2(10) NOT NULL,
    password_hash VARCHAR2(255) NOT NULL,
    created_at DATE NOT NULL,
    last_login DATE NOT NULL,
    PRIMARY KEY(student_id),
    FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE
);


CREATE TABLE reviews (
    review_id INT NOT NULL,
    meal_id INT,
    student_id VARCHAR2(10),
    rating INT NOT NULL,
    review_comment VARCHAR2(500),
    created_at DATE NOT NULL,
    PRIMARY KEY(review_id),
    FOREIGN KEY(meal_id) REFERENCES meals(meal_id) ON DELETE SET NULL,
    FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE SET NULL,
    UNIQUE(meal_id, student_id),
    CONSTRAINT check_rating CHECK(rating BETWEEN 1 AND 10)
);