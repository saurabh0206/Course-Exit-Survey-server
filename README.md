

# Course Feedback Sever

This repository contains the backend implementation of the **Course Feedback System** developed for NIT Calicut. The system facilitates course feedback collection, management, and analysis for three distinct user roles: **Admin**, **Faculty**, and **Student**.  

## Key Features  
- **Admin:**  
  - Manages faculty accounts and their email registrations.  
  - Assigns faculties to courses.  

- **Faculty:**  
  - Logs in using registered credentials using gmail.  
  - Creates surveys for courses.  
  - Adds questions, uploads student email lists via Excel, and sends email notifications for feedback submissions.  
  - Reviews student responses and downloads feedback reports.  

- **Student:**  
  - Logs in with an email ID.  
  - Accesses active feedback forms and submits responses.  

---  

## API Endpoints  

### **Courses**  
| HTTP Method | Endpoint                  | Description                                                                                     |  
|-------------|---------------------------|-------------------------------------------------------------------------------------------------|  
| `POST`      | `/api/courses/create`     | Creates a new course.                                                                          |  
| `GET`       | `/api/courses/`           | Retrieves all courses.                                                                         |  
| `GET`       | `/api/courses/teacher`    | Retrieves courses by teacher email and survey creation status.                                 |  
| `POST`      | `/api/courses/create-update` | Creates or updates a survey for a course and sends notifications to students.                 |  
| `GET`       | `/api/courses/surveys-by-user/{userEmail}` | Retrieves surveys accessible by the user.                                                    |  
| `POST`      | `/api/courses/delete/{courseId}` | Deletes a survey within a course.                                                            |  
| `GET`       | `/api/courses/{id}`       | Retrieves a specific course by ID.                                                            |  
| `DELETE`    | `/api/courses/{id}`       | Deletes a course by ID.                                                                        |  
| `PUT`       | `/api/courses/update-responses/{id}` | Saves student responses for a course survey.                                                  |  

---  

### **Surveys**  
| HTTP Method | Endpoint                  | Description                                                                                     |  
|-------------|---------------------------|-------------------------------------------------------------------------------------------------|  
| `GET`       | `/api/surveys/`           | Retrieves all surveys.                                                                         |  
| `GET`       | `/api/surveys/{id}`       | Retrieves a specific survey by ID.                                                             |  
| `PUT`       | `/api/surveys/update/{id}`| Updates survey details.                                                                        |  
| `PUT`       | `/api/surveys/update-responses/{id}` | Saves responses to a specific survey.                                                        |  
| `DELETE`    | `/api/surveys/delete/{id}` | Deletes a specific survey.                                                                     |  

---  

### **Questions**  
| HTTP Method | Endpoint                  | Description                                                                                     |  
|-------------|---------------------------|-------------------------------------------------------------------------------------------------|  
| `GET`       | `/api/questions/`         | Retrieves all questions for a survey by its ID.                                                |  
| `POST`      | `/api/questions/create`   | Creates new questions for a survey.                                                            |  
| `PUT`       | `/api/questions/update/{id}` | Updates a specific question by its ID.                                                        |  
| `DELETE`    | `/api/questions/delete/{id}` | Deletes a specific question by its ID.                                                        |  

---  

### **Users**  
The UserController handles user authentication and authorization via JWT.  

| HTTP Method | Endpoint                  | Description                                                                                     |  
|-------------|---------------------------|-------------------------------------------------------------------------------------------------|  
| `POST`      | `/api/users/login`        | Authenticates a user using email and password (currently commented out in code).               |  
| `POST`      | `/api/users/google-login` | Authenticates a user using Google Login if enrolled in a course (currently commented out).     |  

---  

## Technologies Used  
- **Backend Framework:** Spring Boot  
- **Database:** MongoDB  
- **Additional Libraries:**   
  - BCrypt for password hashing  
  - JavaMail for email notifications  
  - JWT for secure token-based authentication  

---  

## How to Run the Backend  
1. Clone the repository:  
   ```bash  
   git clone https://github.com/saurabh0206/Course-Exit-Survey-server
   ```  
2. Navigate to the project directory:  
   ```bash  
   cd Course-Exit_Survey_server 
   ```  
3. Configure the `application.properties` file with your MongoDB and email service credentials.  
4. Build and run the application:  
   ```bash  
   mvn spring-boot:run  
   ```  
5. Access the API at `http://localhost:8080`.  

---  

