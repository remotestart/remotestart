# RemoteSTART

### Overview
RemoteSTART is a project management application built to help teams visualize their work and maximize efficiency. After users register for an account they can create a team. Within the team they can manage projects and tasks. Team leaders can assign tasks to team members and team members can report the status of their task. Team members can also create sub-tasks under the tasks they've been assigned. This application follows the MVC design pattern and utilizes Java, JavaScript, MySQL, Spring Boot, HTML, CSS, and Bootstrap.

### Setup
- Fork repository 
- Add application.properties in the resources directory and fill in the required information(see example.properties)
- Run the application remotely to build the DB
- Use the seeder file, found in the Database directory which is in the root of the project, to populate the roles and state_of_completion tables
- You are now up and running with the ability to create/register a user and login.

### Project Organization 
- All Java files can be found in src -> main -> java
- All JavaScript, CSS, HTML, and additional files can be found in src -> main -> resources
