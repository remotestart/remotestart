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

# TEAMS

    # RemoteSTART Dev Team

        # PROJECTS

            # Database
                # MySQL / JPA. Using JPA mapping annotations in the models we need to set up the DB. Each member will be handling different models but make sure and clear any one-to-one, one-to-many, and many-to-many relationship with another table with a team lead. Reach out if you need anything!
            # Models
                # Entity classes representing the DB tables. Make sure to include a constructor, dependency injection, and getters/setters.
            # Repositories
                # We will be using the repositories for both JPA/Hibernate queries and custom queries. Make sure you get any custom query cleared by a team leader.
            # Controllers
                # All Get and Post mappings will go here.
            # Services
                # Any business logic or reused code from the controllers will be pulled out and put into a service. If you are not sure, ask!
            # Views
                # Using Thymeleaf, Bootstrap, and Custom CSS we are going to build out a simple and easy to use interface!

    # UTSA - Student Council

    # Oracle - SA - Java Deals

    # Jim's Car Detailing Sales Team



