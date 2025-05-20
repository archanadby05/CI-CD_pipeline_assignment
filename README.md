# Spring Boot Airline Management System - CI/CD Pipeline

## Project Overview
This project demonstrates a Jenkins pipeline for automating the build, test, and deployment of a Spring Boot Airline Management System application.

---

## Pipeline Features

1. **Checkout**  
   The pipeline clones the Spring Boot project repository from GitHub.

2. **Build Environment Setup**  
   It uses a Docker container with Java and Maven pre-installed to ensure a consistent build environment.

3. **Build**  
   The pipeline runs Maven commands inside the Docker container to clean the project and package the application, skipping tests during the build.

4. **Test**  
   Maven tests are executed inside the same Docker container to validate the code quality.

5. **Deployment**  
   The generated JAR file is securely copied to an AWS EC2 instance using SSH.

6. **Start Application on EC2**  
   The application is started on the EC2 instance by running the JAR file in the background.

---

## Jenkins Pipeline Stages

- **Checkout**: Clones the code from the GitHub repo branch `master`.
- **Build**: Runs `mvn clean package -DskipTests` inside a Maven Docker container.
- **Test**: Runs `mvn test` to execute unit tests.
- **Deploy to EC2**: Copies the JAR to EC2, kills any running instances of the app, and starts the new version.

---

## Jenkinsfile Highlights

- Uses environment variables for EC2 details and credentials.
- Uses `sshagent` for secure SSH operations.
- Runs Maven commands inside a Docker container to isolate dependencies.
- Includes a clean step before building to avoid permission issues.

---

## How to Run

1. Setup Jenkins with Docker and SSH credentials configured.
2. Create a pipeline job and link to the provided `Jenkinsfile`.
3. Run the pipeline to automate build, test, and deployment.
4. Access the Spring Boot application running on the EC2 instance.

---

## Project Files

- `.gitignore` – Ignored files for Git.
- `Dockerfile` – Defines the Docker image for app containerization.
- `Jenkinsfile` – Declarative Jenkins pipeline script.
- `pom.xml` – Maven build configuration.
- `src/main` – Source code for the Spring Boot project.

---

## Notes

- Ensure the EC2 instance has Java installed to run the Spring Boot application.
- SSH credentials and IP address should be updated in the Jenkins pipeline environment variables.
- The pipeline assumes the jar file is named `airline-management-system.jar`.
