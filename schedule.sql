# JPA가 자동으로 생성해주고 관리하지만..!
# 사용할 DATABASE USE
# USE DATABASE schedule-app;


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
    content VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    schedule_id BIGINT
);

# comment 테이블 FK 추가
ALTER TABLE comment
    ADD CONSTRAINT fk_comments_schedule_id
        FOREIGN KEY (schedule_id)
            REFERENCES schedule (schedule_id);

# user 테이블 생성
create table user (
    user_id BIGINT NOT NULL PRIMARY KEY,
    created_date DATETIME,
    modified_date DATETIME,
    email VARCHAR(255),
    gender ENUM ('FEMALE','MALE','OTHER'),
    user_name VARCHAR(255) NOT NULL
);

# 유저 이메일 UNIQUE 설정
alter table user
    drop index user_email_unique;
alter table user
    add constraint user_email_unique unique (email);

# 유저 이름 UNIQUE 설정
alter table user
    drop index user_name_unique;
alter table user
    add constraint user_name_unique unique (user_name);

# user_schedule 중간 테이블 생성
create table user_schedule (
    id BIGINT NOT NULL auto_increment PRIMARY KEY,
    schedule_id BIGINT,
    user_id BIGINT
);

# user_schedule 중간 테이블 FK 설정
alter table user_schedule
    add constraint fk_user_schedule_schedule_id
        foreign key (schedule_id)
            references schedule (schedule_id);
alter table user_schedule
    add constraint fk_user_schedule_user_id
        foreign key (user_id)
            references user (user_id);