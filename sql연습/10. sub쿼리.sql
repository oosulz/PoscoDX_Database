use employees;

# 현재 Fal Bale에 근무하는 부서에서 근무하는 직원의 사번/이름을 출력해보세요
select *
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and concat(a.first_name , ' ' , a.last_name) = 'Fai Bale';

select a.emp_no, a.first_name, a.last_name
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.dept_no = (select b.dept_no
from employees a, dept_emp b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and concat(a.first_name , ' ' , a.last_name) = 'Fai Bale');

# 3.1) 단일 행 연산자 : = , > , < .  >= , <= , <> , !=
# 실습 1
# 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요

SELECT concat(a.first_name , ' ' , a.last_name) as 이름, b.salary as 급여
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.salary < (select avg(salary)
from salaries
where to_date = '9999-01-01');

select avg(salary)
from salaries
where to_date = '9999-01-01';

# 실습 2
# 현재, 직책별 평균 급여 중에 가장 적은 평균급여의 직책이름과 그 평균 급여를 출력하세요

SELECT c.title AS 직책, AVG(b.salary) AS 평균급여
FROM salaries b
JOIN titles c ON b.emp_no = c.emp_no
WHERE b.to_date = '9999-01-01'
  AND c.to_date = '9999-01-01'
GROUP BY c.title
HAVING AVG(b.salary) = (
    SELECT MIN(평균_급여)
    FROM (
        SELECT AVG(b.salary) AS 평균_급여
        FROM salaries b
        JOIN titles c ON b.emp_no = c.emp_no
        WHERE b.to_date = '9999-01-01'
          AND c.to_date = '9999-01-01'
        GROUP BY c.title
    ) AS group_salary
);


SELECT MIN(avg_salary)
FROM (
    SELECT AVG(salary) AS avg_salary
    FROM salaries a
    JOIN titles b ON a.emp_no = b.emp_no
    WHERE b.to_date = '9999-01-01'
    GROUP BY b.title
) AS avg_salaries;

SELECT MIN(평균_급여)
FROM (SELECT c.title AS 직책, AVG(b.salary) AS 평균_급여
FROM salaries b
JOIN titles c ON b.emp_no = c.emp_no
WHERE b.to_date = '9999-01-01' 
AND c.to_date = '9999-01-01'
GROUP BY c.title
) AS group_salary;


select a.first_name, b.salary 
from employees a, salaries b
where a.emp_no = b.emp_no
and b.to_date = '9999-01-01'
and b.salary > 50000;

# 실습 4 
# 현재 각 부서별 최고급여를 받고 있는 직원의 이름, 부서이름, 급여를 출력해 보세요



select concat(a.first_name , ' ' , a.last_name) as 이름, c.salary 
from employees a, salaries c
where a.emp_no = c.emp_no
and c.to_date = '9999-01-01';

# dept_emp
# dept_manager

# 실습 4 
# 현재 각 부서별 최고급여를 받고 있는 직원의 이름, 부서이름, 급여를 출력해 보세요
select CONCAT( .first_name, ' ', a.last_name) AS 이름, c.dept_name, max(d.salary)
from (
	select a.emp_no, b.dept_no
	from employees a, dept_emp b
	where a.emp_no = b.emp_no
	and b.to_date = '9999-01-01')  
as table_dept, 
departments c, 
salaries d
where table_dept.dept_no = c.dept_no
and table_dept.emp_no = d.emp_no
and d.to_date = '9999-01-01'
group by dept_name;




-- union
-- (select a_1.emp_no, c.dept_no, concat(a_1.first_name , ' ' , a_1.last_name) as 이름
-- from employees a_1, dept_manager c
-- where a_1.emp_no = c.emp_no
-- and c.to_date = '9999-01-01');

#1
select a.dept_no , max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by dept_no;

#2

select c.dept_name, concat(a.first_name , ' ' , a.last_name) as name ,d. salary
from employees a, dept_emp b, departments c, salaries d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and (b.dept_no , d.salary) in (select a.dept_no , max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by dept_no);

#2 - 1 from절 서브쿼리 + join 사용
select c.dept_name, concat(a.first_name , ' ' , a.last_name) as name ,d. salary
from employees a, dept_emp b, departments c, salaries d
where a.emp_no = b.emp_no
and b.dept_no = c.dept_no
and a.emp_no = d.emp_no
and b.to_date = '9999-01-01'
and d.to_date = '9999-01-01'
and (b.dept_no , d.salary) in (select a.dept_no , max(b.salary)
from dept_emp a, salaries b
where a.emp_no = b.emp_no
and a.to_date = '9999-01-01'
and b.to_date = '9999-01-01'
group by dept_no);
