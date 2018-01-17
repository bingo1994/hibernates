prompt PL/SQL Developer import file
prompt Created on 2017年12月28日 星期四 by Administrator
set feedback off
set define off
prompt Creating COURSE...
create table COURSE
(
  CID     NUMBER not null,
  CNAME   VARCHAR2(50) not null,
  CCREDIT NUMBER(3)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table COURSE
  add primary key (CID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating EMPLOYEE...
create table EMPLOYEE
(
  ID    NUMBER not null,
  NAME  VARCHAR2(20),
  SEX   VARCHAR2(10),
  PHONE VARCHAR2(20)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table EMPLOYEE
  add primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating STUDENT...
create table STUDENT
(
  SID      NUMBER not null,
  SNAME    VARCHAR2(45) not null,
  SSEX     CHAR(2) not null,
  SDEPT    VARCHAR2(10) not null,
  SAGE     NUMBER(3),
  SADDRESS VARCHAR2(45)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STUDENT
  add primary key (SID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating STUDCOURSE...
create table STUDCOURSE
(
  STUCOURSEID NUMBER not null,
  SID         NUMBER,
  CID         NUMBER,
  GRADE       NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STUDCOURSE
  add primary key (STUCOURSEID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STUDCOURSE
  add foreign key (SID)
  references STUDENT (SID);
alter table STUDCOURSE
  add foreign key (CID)
  references COURSE (CID);

prompt Disabling triggers for COURSE...
alter table COURSE disable all triggers;
prompt Disabling triggers for EMPLOYEE...
alter table EMPLOYEE disable all triggers;
prompt Disabling triggers for STUDENT...
alter table STUDENT disable all triggers;
prompt Disabling triggers for STUDCOURSE...
alter table STUDCOURSE disable all triggers;
prompt Disabling foreign key constraints for STUDCOURSE...
alter table STUDCOURSE disable constraint SYS_C0011714;
alter table STUDCOURSE disable constraint SYS_C0011715;
prompt Deleting STUDCOURSE...
delete from STUDCOURSE;
commit;
prompt Deleting STUDENT...
delete from STUDENT;
commit;
prompt Deleting EMPLOYEE...
delete from EMPLOYEE;
commit;
prompt Deleting COURSE...
delete from COURSE;
commit;
prompt Loading COURSE...
insert into COURSE (CID, CNAME, CCREDIT)
values (11, 'java编程', 6);
insert into COURSE (CID, CNAME, CCREDIT)
values (21, 'c++课程', 4);
insert into COURSE (CID, CNAME, CCREDIT)
values (31, 'oracle', 3);
insert into COURSE (CID, CNAME, CCREDIT)
values (41, 'javaEE', 100);
insert into COURSE (CID, CNAME, CCREDIT)
values (51, 'linux', 1);
insert into COURSE (CID, CNAME, CCREDIT)
values (60, '汉语', 4);
commit;
prompt 6 records loaded
prompt Loading EMPLOYEE...
insert into EMPLOYEE (ID, NAME, SEX, PHONE)
values (4, '李雪', '女', '543342234');
insert into EMPLOYEE (ID, NAME, SEX, PHONE)
values (2, '张三', '男', '12432234');
insert into EMPLOYEE (ID, NAME, SEX, PHONE)
values (3, '刘备', '男', '3233434');
insert into EMPLOYEE (ID, NAME, SEX, PHONE)
values (5, '李雪', '女', '543342234');
commit;
prompt 4 records loaded
prompt Loading STUDENT...
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20040001, '林青霞', 'F ', '计算机系', 23, '上海');
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20040002, '刘德华', 'M ', '外语系', 23, '南京');
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20050003, '成龙', 'M ', '化学系', 21, '山东');
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20050004, '林可欣', 'F ', '计算机系', 23, '北京');
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20050005, '周华健', 'M ', '生物系', 24, '山东');
insert into STUDENT (SID, SNAME, SSEX, SDEPT, SAGE, SADDRESS)
values (20050006, '周润发', 'M ', '数学系', 20, '湖北');
commit;
prompt 6 records loaded
prompt Loading STUDCOURSE...
insert into STUDCOURSE (STUCOURSEID, SID, CID, GRADE)
values (2, 20040001, 11, 90);
insert into STUDCOURSE (STUCOURSEID, SID, CID, GRADE)
values (3, 20040001, 21, 19);
insert into STUDCOURSE (STUCOURSEID, SID, CID, GRADE)
values (4, 20050003, 21, 45);
insert into STUDCOURSE (STUCOURSEID, SID, CID, GRADE)
values (5, 20050004, 41, 99);
insert into STUDCOURSE (STUCOURSEID, SID, CID, GRADE)
values (6, 20050006, 11, 39);
commit;
prompt 5 records loaded
prompt Enabling foreign key constraints for STUDCOURSE...
alter table STUDCOURSE enable constraint SYS_C0011714;
alter table STUDCOURSE enable constraint SYS_C0011715;
prompt Enabling triggers for COURSE...
alter table COURSE enable all triggers;
prompt Enabling triggers for EMPLOYEE...
alter table EMPLOYEE enable all triggers;
prompt Enabling triggers for STUDENT...
alter table STUDENT enable all triggers;
prompt Enabling triggers for STUDCOURSE...
alter table STUDCOURSE enable all triggers;
set feedback on
set define on
prompt Done.
