## Run the PSP-server 
The PSP-server used a Maven-based build system and liquibase for the database evolution. It also uses Spring Boot to run the compiled JAR artifact with an embedded Tomcat Server.

## Prerequisites

* Maven 3.3.3 or above
* Java 8

## Basic setup

These are steps that you will only need to perform the first time you are 
configuring the PSP-server.

### Step 0, clone the repo

```shell
git clone https://github.com/FundacionParaguaya/FP-PSP-SERVER.git
```
### Step 1, create the database

Run with psql the script:

```shell
psql -U YOUR_USER_HERE -f src/main/resources/create_database.sql
```

Yo can also copy and paste the following snippet to your favorite PSQL client:

```
CREATE USER "fp_psp_db" WITH ENCRYPTED PASSWORD 'fp_psp_db';
ALTER ROLE "fp_psp_db" WITH createdb;
\c "dbname=postgres user=fp_psp_db password=fp_psp_db";
CREATE database "fp_psp_db";
SELECT datname FROM pg_database WHERE datistemplate = false;
```

This will generate the initial database, and from this point any DB related changes will be through [Liquibase](Liquibase.md).

Liquibase runs automatically during server startup and update the DB (if 
needed).

### Step 2, compile the application

From the fp-psp-server directory, run:

```shell
mvn clean install -DskipTests
```

If you'd rather run unit tests:

```shell
mvn clean install
```

### Step 3, run the application

Start the server:

```shell
java -jar target/*.jar
```

And that is it ;), your server will be launched by default at http://localhost:8080.

## What's next?

- Do you want to make changes to the database using Liquibase?  [Read the Liquibase instructions.](LIQUIBASE.md)

- Do you want to provide custom configuration on applicaiton startup? [Read the CUSTOM_CONFIG instructions](CUSTOM_CONFIG.md)
