# Fuel Quota Management System

A Java-based application for managing fuel quotas with MySQL database integration.

## Prerequisites

- Java 17 or higher
- Maven
- MySQL Server

## Setup

1. Clone the repository
2. Create MySQL database using `src/main/resources/schema.sql`
3. Update database credentials in `DatabaseUtil.java` if needed
4. Run `mvn clean install` to build the project

## Running the Application

1. Run `mvn clean install` to build the project
2. Run `mvn exec:java -Dexec.mainClass="com.ipv404.App"` to run the application
