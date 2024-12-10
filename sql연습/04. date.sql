#문자열 함수
use employees;

select upper('seoul') , ucase('SeouL') from dual;
select upper(first_name) from employees;

select lower('seoul') , lcase('SeouL') from dual;
select lower(first_name) from employees;

# substring (문자열 , index , length)
select substring('hello world',3,2) from dual;

select first_name, hire_date
  from employees
where substring(hire_date,1,4) = '1989'; 

# date_format
# default format
# date %Y-%m-%d
# datetime %Y-%m-%d %h:%i:%s
select date_format(now(), '%Y년 %m월 %d일 %h:%i:%s');
select date_format(now(), '%d %b \'%y %h:%i:%s');

-- cast
select '12345' + 10, concat('12345',10), cast('12345' as INT)+ 10;
select date_format(cast('2024-12-10' as date), '%Y년 %m월 %d일');
select cast(cast(1-2 as unsigned) as signed) from dual;
select cast(cast(1-2 as unsigned) as int) from dual;
select cast(cast(1-2 as unsigned) as integer) from dual;

-- period_diff
select first_name,
		hire_date,
		period_diff(date_format(curdate(), '%y%m'), date_format(hire_date, '%y%m')) as '근무개월'
        from employees;

# date_add(=addDate), date_sub(=subdate)        
# 각 사원의 근속 연수가 5년이 되는 날에 휴가를 보내준다면 각 사원의 5년 근속 유가 날짜는?

select first_name, hire_date,
	date_add(hire_date,interval 5 year)
    from employees;
    



