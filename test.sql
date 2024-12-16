select *  from user;
select * from category;
select * from book;
select * from cart;

select * from orders;

select no, number, payment, shipping, status, user_no from orders where no = 1 and where user_no = 3;

delete from user;
delete from category;
delete from book;
delete from cart;
delete from orders;
delete from orders_book;

ALTER TABLE user AUTO_INCREMENT = 1;
ALTER TABLE book AUTO_INCREMENT = 1;
ALTER TABLE category AUTO_INCREMENT = 1;
ALTER TABLE orders AUTO_INCREMENT = 1;

TRUNCATE TABLE user;

SELECT b.orders_no, b.book_no, b.quantity, b.price 
FROM orders_book b 
JOIN orders o ON o.no = b.orders_no 
JOIN user u ON u.no = o.user_no 
WHERE u.no = ? AND b.book_no = ?;