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
    drop index UKob8kqyqqgmefl0aco34akdtpe;
alter table user
    add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

# 유저 이름 UNIQUE 설정
alter table user
    drop index UKlqjrcobrh9jc8wpcar64q1bfh;
alter table user
    add constraint UKlqjrcobrh9jc8wpcar64q1bfh unique (user_name);

# user_schedule 중간 테이블 생성
create table user_schedule (
    id BIGINT NOT NULL auto_increment PRIMARY KEY,
    schedule_id BIGINT,
    user_id BIGINT
)

# user_schedule 중간 테이블 FK 설정
alter table user_schedule
    add constraint FKdd4cwg959bmy4551iiivx4wdw
        foreign key (schedule_id)
            references schedule (schedule_id);
alter table user_schedule
    add constraint FKmsyiiyw4bv8y8sv4dbh6k481a
        foreign key (user_id)
            references user (user_id);