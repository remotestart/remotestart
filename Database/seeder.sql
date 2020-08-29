USE remotestart_db;

# Team Roles
insert into roles ( role)
values ('Team Leader'),
       ('Team Member');

# States for Tasks/Subtasks
insert into state_of_completion (state_of_completion)
values ('Not Started'),
       ('In Progress'),
       ('Completed');

# Teams
insert into teams (name)
values (),
       (),
       (),
       (),
       ();

# Projects
insert into projects ( name, description, start_date, deadline, completion_date)
values ( 'Hana and Alice (Hana to Arisu)', 'Duis consequat dui nec nisi volutpat eleifend. Donec ut dolor. Morbi vel lectus in quam fringilla rhoncus.', '2019-01-02', '2019-11-13', '2020-03-11');

# Tasks
insert into tasks ( title, description)
values ( 'Zakochani', 'Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');

# Subtasks
insert into sub_tasks ( title, description)
values ( 'Christopher Strong', 'Fusce consequat. Nulla nisl. Nunc nisl.');


