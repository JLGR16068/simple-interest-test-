# simple-interest-test-

## Requirements
 
 Must have tinstalled:
- Java 
- Maven
- Docker

## How to run

1. First clone respository
2. Be placed in the root directory (where pom.xml is visible)
3. Build the docker image with the following command

```
mvn clean package dockerfile:build
```
5. Run the docker container using the image executing one of the following options:
    - `docker-compose up -d`
    - `docker run -d -p 8088:8088 --name simple-interes -e TZ='America/Mexico_City' aplazo/simple-interest-test:0.0.1-SNAPSHOT`
6. Consume the service expose to http://localhost:8088/simple-interest through POST method
    - Example body:
    ```
    {
        "amount": 1200.00,
        "terms": 3,
        "rate": 0.05
    }
    ```
