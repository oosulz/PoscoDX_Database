#1) 집계쿼리 select 절에 집계함수(count, max,min, avg,variance, strdev,sum)
select avg(salary) , sum(salary)
from salaries;

#2) select절에 집계(그룹)함수가 있는 경어 어떤 칼럼도 select절에 올 수 없다
# emp_no는 아무 의미가 없음

#3) query 순서
# from -> where -> group by -> having -> order by -> select
  
  #예제 사원별 평균 연봉은?
  #group by에 참여하고 있는 컬럼은 projection이 가능하다 / select 절에 올 수 있다
select avg(salary)
	from salaries
	group by emp_no;

#5) 예제 : 평균월급이 60000 이상인 사원의 사번과 평균 연봉을 출력하세요 -> having 절 사용
select avg(salary) as avg_salary
from salaries
group by emp_no
having avg_salary > 60000
order by avg_salary asc;

select emp_no, avg(salary), sum(salary)
from salaries
where emp_no = 10060;

select emp_no,avg(salary), sum(salary)
from salaries
group by emp_no
having emp_no = 10060;