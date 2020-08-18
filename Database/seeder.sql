USE remotestart_db;

insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Hana and Alice (Hana to Arisu)', 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', '2019-01-02', '2019-11-13', '2020-03-11');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Come Out and Play', 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', '2019-01-02', '2019-11-15', '2019-12-08');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Very Natural Thing, A', 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', '2019-01-02', '2019-12-20', '2020-03-29');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Main Prem Ki Diwani Hoon', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.', '2019-01-02', '2020-07-10', '2020-06-21');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Rocket Science', 'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '2019-02-01', '2019-12-14', '2020-07-09');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Pete''s Dragon', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante.', '2019-01-02', '2020-01-09', '2019-10-19');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'New York Doll', 'Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', '2019-01-02', '2019-10-17', '2020-01-24');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Casey Jones', 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', '2019-01-02', '2020-05-23', '2020-04-23');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Endangered Species', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '2019-01-02', '2020-02-27', '2020-05-27');
insert into projects ( name, description, start_date, deadline, completion_date) values ( 'Killjoy 3', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', '2019-01-02', '2020-06-16', '2020-06-19');

insert into tasks ( title, description) values ( 'Zakochani', 'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');
insert into tasks ( title, description) values ( 'Amish Grace', 'In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl.');
insert into tasks ( title, description) values ( 'Such a Long Journey', 'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
insert into tasks ( title, description) values ( 'Touch of Spice, A (Politiki kouzina)', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.');
insert into tasks ( title, description) values ( 'What Would Jesus Buy?', 'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');

insert into sub_tasks ( title, description) values ( 'Christopher Strong', 'Fusce consequat. Nulla nisl. Nunc nisl.');
insert into sub_tasks ( title, description) values ( 'Oklahoma!', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.');
insert into sub_tasks ( title, description) values ( 'My Best Fiend (Mein liebster Feind)', 'Aliquam quis turpis eget elit sodales scelerisque.');
insert into sub_tasks ( title, description) values ( 'Seven Days in May', 'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
insert into sub_tasks ( title, description) values ( 'El chocolate del loro', 'Sed ante.');

insert into state_of_completion ( state_of_completion) values ( 'Not Started');
insert into state_of_completion ( state_of_completion) values ( 'In Progress');
insert into state_of_completion ( state_of_completion) values ( 'Completed');

insert into roles ( role) values ( 'Team Leader');
insert into roles ( role) values ( 'Team Member');

insert into teams ( name) values ( 'I like turtles');
insert into teams ( name) values ( 'I like trains');
insert into teams ( name) values ( 'What are those?!');
insert into teams ( name) values ( 'YAAAS');
insert into teams ( name) values ( 'Jailbreakers');
insert into teams ( name) values ( 'Guitar Hero Pros');