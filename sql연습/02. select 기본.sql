use employees;
select * from departments;

# projection
# 예제2 employees 테이블에서 직원의 이름 성별 입사일 출력
SELECT first_name, gender, hire_date
    FROM employees;

# alias
select first_name as '이름',
	gender as '성별',
	hire_date as '입사일'
	from employees;
    

# distinct
select distinct(title)
from titles;

# where 

# 비교 연산자
select first_name,
	gender,
	hire_date
from employees
where hire_date < '1991-01-01'
and gender = 'f';

# 논리 연산자
select emp_no, dept_no
	from dept_emp
	where dept_no in ( 'd005', 'd009');

#LIKE
  SELECT concat( first_name, ' ', last_name ) AS 이름,
         hire_date AS 입사일
    FROM employees
   WHERE hire_date LIKE '1989%';

#예제 9 , employee 테이블에서 직원 이름 성별 입사일을 입사일 순으로 출력
SELECT first_name, gender, hire_date
    FROM employees
    order by hire_date asc; 
    
#예제 10, salaries 테이블에서 2001년 월급을 가장 높은순으로 사번, 월급순으로 출력
SELECT emp_no, salary
    FROM salaries
    where from_date like '2001%'
    and to_date like '2001%'
    order by salary desc;
    