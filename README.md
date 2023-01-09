Elba Technologies proficiency test

This is a Spring Boot application build on top of maven with purpose to read excel files, from which 
it creates entities like  Employee and Department and stores them in a PostgreSQL database.
Entities cannot be duplicated in database. If the excel file gets re-imported with new updates than:
the employees that already exist, gets updated.
the employees that no longer are present in the excel file, gets deleted.
the new employees gets stored in db.

To run this application, the machine should have maven installed, jdk 17, postgresql, and an IDE to open the project.
After git clone, reload the maven dependencies to resolve and download required ones. Database should be configured also.
After configuring .yml file with db user credentials and db connection is all-set, a database
with name "elba_proficiency_demo" should be created. 

After the dependencies resolved and database all-set, run command:
"mvn clean flyway:migrate -Dflyway.configFiles=flyway.conf" to execute flyway schema migration file. Now 
that the schema with required tables is created, the application is ready to run.

The application provides four end-points: 

http://localhost:8080/admin/import?filePath={file-location}
http://localhost:8080/admin/employee/all   
http://localhost:8080/admin/employee/{employee-name}
http://localhost:8080/admin/department/all

The application has unit test. One way to run test is to hit run through the application
test package or using maven command "mvn test". 






