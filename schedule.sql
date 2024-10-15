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


# comment 테이블 생성
create table comment (
    comment_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_date DATETIME,
    modified_date DATETIME,
    content varchar(255) NOT NULL,
    user_name varchar(255) NOT NULL,
    schedule_id BIGINT
);

# comment 테이블 FK 추가
ALTERR TABLE comment
    ADD CONSTRAINT FKsy51iks4dgapu66gfj3mnykch
        FOREIGN KEY (schedule_id)
            REFERENCES schedule (schedule_id)