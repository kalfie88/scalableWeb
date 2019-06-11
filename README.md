# Scalable Web Api

Rest Api that accepts two Json files and provides an output with the differences between them. The output will give you the information that is only on the right side, the information that is only on the left side, and the differences between the both of them combine.
The Api has an embedded H2 schema were you can also save the result of the comparison, with operations like find and delete.



## Project Structure

### src/main/java

Under this path we have several packages mentioned below:

1. com.waes.scalableWeb -> We have the Spring Boot Application and the Swagger configuration files.

2. com.waes.scalableWeb.common -> In here we have the classes in charge of the processing of the Json file, also util classes and custom Exceptions.

3. com.waes.scalableWeb.controller -> Application controllers

4. com.waes.scalableWeb.dto -> DTO class to separate the service layer of the persistance layer.

5. com.waes.scalableWeb.entities -> Objects to map the entities needed in the application

6. com.waes.scalableWeb.enums -> An Enum to manage the flags left and right 

7. com.waes.scalableWeb.repository -> Interface of the repository to interact with the H2 schema

8. com.waes.scalableWeb.service -> Application services 


### src/main/resources

1. templates -> There are 2 input Json example files in here in case you need them for the testing of the endpoints.

2. H2 files -> data.sql is a dummy data for H2, and schema.sql is the script to create the schema in H2.


### src/test/java

1. com.waes.scalableWeb -> Integration test

2. com.waes.scalableWeb.common -> Unit test for JsonManipulationManager class

3. com.waes.scalableWeb.dto -> Unit test to check the DTO's functionality

4. com.waes.scalableWeb.repository -> Unit test for saving, retrieving and deleting info from the H2 schema



## Getting Started

This is a Spring Tool Suite(Eclipse) based, Gradle project written in Java 8, with Spring Boot, Lombok, and Swagger.
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.



### Prerequisites

So please follow these instructions so you can setup the environment properly.

1. Get Git in your local -> In case you don’t have it already, go ahead and follow this guide: [Install-Git](https://www.atlassian.com/git/tutorials/install-git)

2. Get your IDE -> Since this is a Gradle project you can use the IDE of your preference, (I used Spring Tool Suite) just make sure to import the project as an “Existing Gradle Project”

3. Point your IDE to the right JRE version -> This project is build over Java 8 so please make sure that your Java Build Path is pointing to the JRE 1.8 version. You’ll find this option under: Preferences-> Java -> Installed JREs (Eclipse based IDE)

4. Get Lombok -> Lombok is an external jar that needs to be added into your IDE. For more information on how to add Lombok to your IDE (Eclipse or IntelliJ) follow this guide: [Lombok-IDE](https://www.baeldung.com/lombok-ide) Also there’s a copy of the jar file (Lombok.jar) in the project under the root folder.


#### For testing

Since the project is a backend project, you’ll need a testing tool to hit the endpoints such as Postman. 


##### Installing Postman

1. Download the app from the official site: [Postman](https://www.getpostman.com/downloads/)

2. Import the Json file containing the Collection of endpoints you’ll need to test the app. You can find the file ScalableWebAPI.postman_collection.json under the root folder of the project. Just hit the “Import” button on the left corner, choose Import File, and drag and drop the file there. Or if you prefer you can use a shareable link, so click the Import button on the left corner, choose Import From link and copy this link [Postman Collection](https://www.getpostman.com/collections/ed7ad3576d48fec01248)



### Installing

Once your development environment is setup, we can go ahead and get the project into your IDE and run it. So please follow the next steps to do so:

1. Clone the repo into your local: https://github.com/kalfie88/scalableWeb.git or download the zip folder.

```
git clone https://github.com/kalfie88/scalableWeb.git
```

2. Import the project into your IDE: Right click -> Import -> Gradle -> Existing Gradle Project and follow the wizard.

3. Try Clean and Build first to make sure everything is compile and really. 

4. Run the project: This is a Spring Boot project, so just go to src/main/java/com.waes.scalableWeb/ScalableWebApplication.java and right click on it -> Run As -> Spring Boot App.

5. The project is running under: http://localhost:8080/

6. To hit the Endpoints: Open Postman and run each of the endpoints in the collection imported in the section above (Prerequisites)


#### Endpoints available 

To see the documentation of the endpoints available you can refer to Swagger.

1. Run the app following the step #4 of the Installing section

2. Open a browser and type:

```
http://localhost:8080/swagger-ui.html#/

```

You’ll find the description for the endpoints here

```
http://localhost:8080/swagger-ui.html#/scalable-web-controller

```



## Running the tests

For this project, we have one integration test, and several unit test under the src/main/test path.
But for a more visual test you can use Postman.


### Testing using Postman

If you don’t want to use the Collection file mentioned above, you can create your own requests and use the example Json under src/main/resources/templates to send in the body of the requests: 

```
localhost:8080/v1/diff/{id}/left
localhost:8080/v1/diff/{id}/right

```



## Author

* **Katherine Alfaro Ramirez** - *Initial work* - [kalfie88](https://github.com/kalfie88)
