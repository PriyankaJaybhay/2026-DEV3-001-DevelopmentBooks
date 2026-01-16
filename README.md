# 2026-DEV3-001-DevelopmentBooks
This is a spring boot application to calculate book prices in the basket based on the discount using TDD approach
# Setup Guide

Quick guide to get this project running on your machine.

## What You Need

1. **Java 17 or higher**
   - Check: `java -version`
   - In case java is not available on your machine. You can download it from : https://www.oracle.com/java/technologies/downloads/

2. **Maven 3.6+**
   - Check: `mvn -version`
   - In case maven is not available on your machine. You can download it from Download: https://maven.apache.org/download.cgi

3. **IDE (Optional but recommended)**
   - IntelliJ IDEA

## Installation Steps

### 1. Clone/Download the Project


git clone https://github.com/PriyankaJaybhay/2026-DEV3-001-DevelopmentBooks.git

cd 2026-DEV3-001-DevelopmentBooks


Or download and extract the ZIP file.

### 2. Build the Project

run the below command in the command prompt inside folder `2026-DEV3-001-DevelopmentBooks`

`mvn clean install`


This will:
- Download all dependencies
- Compile the code
- Run all tests
- Create a JAR file in `target/` folder

### 3. Run the Application

Now run the application using below command :

`mvn spring-boot:run`

Or run the JAR directly:

`java -jar target/development-books-1.0.0.jar`


Application starts at: **http://localhost:8080**

### 4. Verify It's Running

Open Postman and test:

```
POST http://localhost:8080/api/basket/calculate-price

Content-Type: application/json

{
  "books": ["CLEAN_CODE", "THE_CLEAN_CODER"]
}
```

Should return: `{"totalPrice": 95.0}`



## Troubleshooting

**Port 8080 already in use?**
- Stop other applications using port 8080
- Or change port in `src/main/resources/application.properties`:
  ```
  server.port=8081
  ```

**Maven not found?**
- Make sure Maven is in your PATH
- Windows: Add Maven `bin` folder to System Environment Variables
- You can set path M2_HOME environment to the bin folder inside your maven folder

**Java version mismatch?**
- This project requires Java 17+
- Set JAVA_HOME environment variable to Java 17 installation

**Build fails?**
- Delete `.m2/repository` folder and run `mvn clean install` again
- Check internet connection (Maven needs to download dependencies)

## IDE Setup

### IntelliJ IDEA
1. Open → Select project folder
2. Wait for Maven import to complete
3. Right-click `BookStoreApplication.java` → Run


## Project Structure

```
Basic folder layout:
- src/main/java/com/bookstore - main code
  - controller - REST endpoints
  - dto - request/response objects
  - model - Book and Basket classes
  - service - price calculation logic
- src/test/java - test cases
- pom.xml - Maven dependencies
```


## Next Steps

- Check main README.md for API documentation
- Test with Postman using examples provided and any test examples you want to test
- Run tests to see TDD approach
- Explore the code starting from `BookStoreApplication.java`

## Need Help?

- Check if Java 17 is installed: `java -version`
- Check if Maven is installed: `mvn -version`
- Make sure you're in the project root directory
- Try `mvn clean` before `mvn install`
