#Mealer - save shopping list

#####Simple application that allows you to search through API, save dishes with needed ingredients created with Spring Boot, Vaadin and MongoDB

### Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

### General info
The application reads data from the API www.themealdb.com (when searching for dishes), then allows you to select "missing" ingredients and save them to the database (MongoDB). The name of the dish and the ingredients are recorded. 
We have access to view and edit data related to the "shopping list" (we can delete individual ingredients as well as entire lists).
The application uses Spring Security, creating users is possible via API. Sample POST (/api/registrations):
```
{
    "username": "test",
    "password": "test1",
    "name": "test",
    "email": "test@local.com",
    "roles": ["user", "admin"]
}
```
The simple frontend was written using the Vaadin framework.
Tests were written to check registration payload. 

### Setup
To run the application, specify the path to the MongoDB database in the application.properties file
The application can then be run using the 
```
mvn spring-boot
```
