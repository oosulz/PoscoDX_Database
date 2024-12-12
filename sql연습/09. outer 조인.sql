select * from department;
select * from employee;


insert into department values(null,'총무');
insert into department values(null,'개발');
insert into department values(null,'영업');
insert into department values(null,'기획');

insert into employee values(null,'둘리',1);
insert into employee values(null,'마이콜',2);
insert into employee values(null,'또치',3);
insert into employee values(null,'길동', null);


# left 조인
select a.name as '이름', b.name as '부서'
from employee a left join department b on a.department_id = b.id;

# right 조인
select a.name as '이름', b.name as '부서'
from employee a right join department b on a.department_id = b.id;

