use kdt;

create table community (
    id bigint not null auto_increment primary key,
    title varchar(20) not null,
    content varchar(100) not null,
    writer varchar(10) not null,
    registerd timestamp default current_timestamp
);

select * from community;

-- 이건 진짜 필요한 게 아니라 mysql workbench에서 실행하는 참고용 파일