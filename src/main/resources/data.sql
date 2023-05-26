insert into course (id, name)
values (10001, 'JPA in 50 Steps');
insert into course (id, name)
values (10002, 'JPA in 100 Steps');
insert into course (id, name)
values (10003, 'Spring in 50 steps');
insert into course (id, name)
values (10004, 'Dummy1');
insert into course (id, name)
values (10005, 'Dummy2');
insert into course (id, name)
values (10006, 'Dummy3');
insert into course (id, name)
values (10007, 'Dummy4');
insert into course (id, name)
values (10008, 'Dummy5');
insert into course (id, name)
values (10009, 'Dummy6');
--
insert into passport(id, number)
values (40001, '40001');
insert into passport(id, number)
values (40002, '40001');
insert into passport(id, number)
values (40003, '40001');
--
insert into student(id, name, passport_id)
values (20001, 'Corneliu', 40001);
insert into student(id, name, passport_id)
values (20002, 'Iosif', 40002);
insert into student(id, name, passport_id)
values (20003, 'Ioana', 40003);
--
insert into review(id, rating, description, course_id)
values (50001, '5', 'Great Course', 10001);
insert into review(id, rating, description, course_id)
values (50002, '3', 'Great Course2', 10001);
insert into review(id, rating, description, course_id)
values (50003, '2', 'Great Course3', 10002);
--
insert into course_student(student_id, course_id)
values (20001, 10001);
insert into course_student(student_id, course_id)
values (20002, 10001);
insert into course_student(student_id, course_id)
values (20003, 10001);
insert into course_student(student_id, course_id)
values (20001, 10002);
-- 12,3,13,7
