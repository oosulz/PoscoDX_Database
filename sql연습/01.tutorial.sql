# 수학함수, 사칙연산도 된다
select version(), current_time, current_date, now() FROM dual;

# 대소문자 구분이 없다.
select VERSION(), current_DATE, now() FROM dual;

# 테이블 생성
create table pet(
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
    );

# 선택
select * from pet;

# 자료형 설명
describe pet;
desc pet;

# 테이블 삭제
drop table pet;

# insert (삽입)
insert into pet(name, owner, species, gender, birth) values ("뽀식이","뽀식이주인","사모예드","M","2024-12-09");

update pet set name = "뽀시기" where name = "뽀식이";
update pet set death = null where death = "0000-00-00";

delete from pet where name = "뽀시기";

#load data : mysql (CLI) 
load data local infile '/home/oosulz/pet.txt' into table pet; 

# select 연습
	select name,species 
    from pet
    where birth = '1993-02-04';
    
# select 연습 2
	select name,species 
    from pet
    where birth >= '1998-01-01';

# select 연습 3
	select name,species,gender 
    from pet
    where species = 'dog'
    and gender = 'f';
    
# select 연습 4
	select name,species,gender 
    from pet
    where species = 'snake'
    or species = 'bird';

# select 연습 5
	select name,birth,gender 
    from pet 
    where death is not null;
    
    #where species = 'snake'
    
    #or species = 'bird';
    
    