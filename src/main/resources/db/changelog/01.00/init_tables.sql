--liquibase formatted sql

--changeset ponomarev.s:create_tables

create schema if not exists exam;

create table if not exists exam.question
(
    id bigserial primary key,
    text varchar(250) not null,
    level varchar(30) not null
);

create table if not exists exam.answer
(
    id bigserial primary key,
    text varchar(250) not null,
    is_correct boolean not null,
    question_id bigint not null references exam.question(id) on delete cascade
);

--rollback drop table if exists exam.question cascade;
--rollback drop table if exists exam.answer cascade;