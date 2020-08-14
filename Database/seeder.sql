USE remotestart_db;

insert into projects (id, name, description, deadline, completion_date) values (1, 'Hana and Alice (Hana to Arisu)', 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', '11/13/2019', '03/05/2020');
insert into projects (id, name, description, deadline, completion_date) values (2, 'Come Out and Play', 'Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.', '11/15/2019', '12/08/2019');
insert into projects (id, name, description, deadline, completion_date) values (3, 'Very Natural Thing, A', 'In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus.', '12/20/2019', '03/29/2020');
insert into projects (id, name, description, deadline, completion_date) values (4, 'Main Prem Ki Diwani Hoon', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.', '07/10/2020', '06/21/2020');
insert into projects (id, name, description, deadline, completion_date) values (5, 'Rocket Science', 'Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam.', '12/14/2019', '07/09/2020');
insert into projects (id, name, description, deadline, completion_date) values (6, 'Pete''s Dragon', 'Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante.', '01/09/2020', '10/19/2019');
insert into projects (id, name, description, deadline, completion_date) values (7, 'New York Doll', 'Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi.', '10/17/2019', '01/24/2020');
insert into projects (id, name, description, deadline, completion_date) values (8, 'Casey Jones', 'Morbi sem mauris, laoreet ut, rhoncus aliquet, pulvinar sed, nisl. Nunc rhoncus dui vel sem.', '05/23/2020', '04/08/2020');
insert into projects (id, name, description, deadline, completion_date) values (9, 'Endangered Species', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem.', '02/27/2020', '05/27/2020');
insert into projects (id, name, description, deadline, completion_date) values (10, 'Killjoy 3', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', '06/16/2020', '11/03/2019');

insert into tasks (id, title, description) values (1, 'Zakochani', 'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');
insert into tasks (id, title, description) values (2, 'Amish Grace', 'In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl.');
insert into tasks (id, title, description) values (3, 'Such a Long Journey', 'Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus.');
insert into tasks (id, title, description) values (4, 'Touch of Spice, A (Politiki kouzina)', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst.');
insert into tasks (id, title, description) values (5, 'What Would Jesus Buy?', 'Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');

insert into sub_tasks (id, title, description) values (1, 'Christopher Strong', 'Fusce consequat. Nulla nisl. Nunc nisl.');
insert into sub_tasks (id, title, description) values (2, 'Oklahoma!', 'Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue.');
insert into sub_tasks (id, title, description) values (3, 'My Best Fiend (Mein liebster Feind)', 'Aliquam quis turpis eget elit sodales scelerisque.');
insert into sub_tasks (id, title, description) values (4, 'Seven Days in May', 'Morbi ut odio. Cras mi pede, malesuada in, imperdiet et, commodo vulputate, justo. In blandit ultrices enim. Lorem ipsum dolor sit amet, consectetuer adipiscing elit.');
insert into sub_tasks (id, title, description) values (5, 'El chocolate del loro', 'Sed ante.');

insert into state_of_completion (id, state_of_completion) values (1, 'Not Started');
insert into state_of_completion (id, state_of_completion) values (2, 'In Progress');
insert into state_of_completion (id, state_of_completion) values (3, 'Completed');
insert into state_of_completion (id, state_of_completion) values (4, 'Not Started');
insert into state_of_completion (id, state_of_completion) values (5, 'In Progress');
insert into state_of_completion (id, state_of_completion) values (6, 'Completed');
insert into state_of_completion (id, state_of_completion) values (7, 'Not Started');
insert into state_of_completion (id, state_of_completion) values (8, 'In Progress');
insert into state_of_completion (id, state_of_completion) values (9, 'Completed');
insert into state_of_completion (id, state_of_completion) values (10, 'Completed');

insert into roles (id, role) values (1, 'user');
insert into roles (id, role) values (2, 'Team Leader');
insert into roles (id, role) values (3, 'Team Member');
insert into roles (id, role) values (4, 'Team Member');
insert into roles (id, role) values (5, 'Team Leader');
insert into roles (id, role) values (6, 'Team Member');

insert into teams (id, name) values (1, 'I like turtles');
insert into teams (id, name) values (2, 'I like trains');
insert into teams (id, name) values (3, 'What are those?!');
insert into teams (id, name) values (4, 'YAAAS');
insert into teams (id, name) values (5, 'Jailbreakers');
insert into teams (id, name) values (6, 'Guitar Hero Pros');