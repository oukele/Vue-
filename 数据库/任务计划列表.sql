use data_test;
drop table todo;
create table `todo`
(
	id int primary key auto_increment ,
	name varchar(50) ,
	detail varchar(1000) not null,
	created_at datetime default now(),
	finished_at datetime default now(),
	state int default 0,
	memo varchar(200)
);

insert into `todo`(name,detail,finished_at) values('欧大仙','今天要写完老师布置的作业','2018-11-30 23:00:00');
insert into `todo`(name,detail,finished_at) values('刘大仙','今天不用写作业','2018-11-30 16:00:00');
insert into `todo`(name,detail,finished_at) values('刘明凯','今天要跑操，跑20步','2018-11-30 17:00:00');
insert into `todo`(name,detail,finished_at) values('李逍遥','今天要练武功，和大师过过招','2018-12-01 23:30:00');

select id as 编号, name as 名字, 
	detail as 任务,created_at as 任务创建时间, finished_at as 任务应完成时间,state as 状态,memo as 备注 
	from todo;