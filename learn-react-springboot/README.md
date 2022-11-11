# learn-react-springboot

This repository is home to a gitpod environment containing the following:

A Postgres SQL database

Java Microservices API built with the Spring-Boot framework.  RESTful API endpoints are provided to support the front-end application in searching for Customers and Patients, and retrieving details about them and their associated medical Conditions, Medications, Encounters, and Observations.  

A react.js implementation that interacts with the java microservices API.

This repository also includes PostgreSQL DDL migration scripts, which allows database tables to be created and modified using the Flyway Database Migration framework. Any DDL scripts that have not already been run, will be executed upon the next startup of the application.  In fact the entire database structure can be created by starting the application with an empty database.


## Building the Application

This application is built using Apache Maven.  To build an executable "fat" jar, run the following command from the project root:

`mvn clean install`

## Running the Application

The executable jar can be deployed to an existing Java Servlet container such as Apache Tomcat Server, or (preferrable) run as a standalone Java application with an embedded Tomcat Server.  However the application is run, there is a dependency that the runtime environment contain the following environment variables used to create the database connection:
```
POSTGRES_HOST (Database Host)
POSTGRESS_DB (Name of Database)
POSTGRES_USER (Database User Name)
POSTGRES_PASSWORD (Dagabase User Password)
```

To run the application from a bash shell at the project root (after building via maven):

1) Set the required env variables:

```
       export POSTGRES_HOST=somedb.somehost.com
       export POSTGRESS_DB=esis
       export POSTGRES_USER=someuser
       export POSTGRES_PASSWORD=somereallystrongpassword:-)
```       

2) Execute:

```    
       java -jar src/target/uscis-esis.jar
```       

-or-

```       
       mvn spring-boot:run
```

To build a Docker image and run the application in a Docker container, execute:

```
docker build -t backend -f Dockerfile .
docker run --env PG_HOST=somedb.somehost.com --env PG_DB=esis --env PG_USER=someuser --PG_PASS=somereallystrongpassword:-) backend

```

## API Specifications

Swagger Doc Specifications are automatically generated using the SpringFox framework, and are visible at:

https://[host:port]/api/swagger-ui/index.html
