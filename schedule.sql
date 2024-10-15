# 사용할 DATABASE USE
USE DATABASE schedule-app


## SQL DDL 관련

# schedule 테이블 생성
CREATE TABLE IF NOT EXISTS schedule (
    scheduleId BIGINT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(20) NOT NULL UNIQUE,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    startDate DATETIME NOT NULL,
    endDate DATETIME NOT NULL,
    createdDate DATETIME NOT NULL,
    modifiedDate DATETIME NOT NULL
);


## SQL DML 관련