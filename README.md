
# 📝 Course Exit Survey System – Backend

## 📌 Overview

This backend system allows faculty to collect structured course exit feedback from students. Admins onboard faculty and assign subjects. Faculty members create subject-specific surveys and notify students via email. Students submit responses, and faculty can view/download analytics.

## 🛠️ Tech Stack

- **Framework**: Spring Boot  
- **Database**: MongoDB  
- **Authentication**: Google OAuth 2.0  
- **Exporting**: PDF generation for survey responses

---

## 👥 User Roles

### 👨‍💼 Admin
- Adds faculty via email
- Assigns subjects to faculty

### 👩‍🏫 Faculty
- Logs in via email
- Creates survey questions per subject
- Uploads student email list via Excel
- Views all responses
- Downloads PDF report per subject

### 👨‍🎓 Student
- Logs in using Gmail (OAuth)
- Views surveys for their assigned subjects
- Submits feedback (once per subject)

---

## 📂 Project Structure

```bash
├── src/
│   ├── controller/         # REST controllers
│   ├── service/            # Business logic
│   ├── model/              # MongoDB models
│   ├── repository/         # DB access layer
│   └── config/             # OAuth & security
├── application.properties  # MongoDB, OAuth, etc.
└── README.md
````

---

## 📮 Key API Endpoints

| Method | Endpoint                 | Description                    |
| ------ | ------------------------ | ------------------------------ |
| `POST` | `/admin/addFaculty`      | Add faculty user               |
| `POST` | `/faculty/createSurvey`  | Create questions for subject   |
| `POST` | `/faculty/uploadEmails`  | Upload Excel of student emails |
| `GET`  | `/student/surveys`       | Get available surveys          |
| `POST` | `/student/submit`        | Submit survey response         |
| `GET`  | `/faculty/viewResponses` | View all responses             |
| `GET`  | `/faculty/downloadPdf`   | Download PDF of responses      |

> Full documentation in [`APIs.pdf`](./APIs.pdf)

---

## 🚀 Getting Started

### 🔧 Prerequisites

* Java 17+
* MongoDB
* Google OAuth credentials

### 🔑 Setup

1. Clone the repo
2. Add your `application.properties` file with:

   ```properties
   spring.data.mongodb.uri=your_mongodb_uri
   spring.security.oauth2.client.registration.google.client-id=your_client_id
   spring.security.oauth2.client.registration.google.client-secret=your_client_secret
   ```
3. Run the backend:

   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📄 PDF Export Sample

Faculty can download all responses in PDF format, grouped by subject. Each PDF includes:

* Survey questions
* Aggregated responses
* Individual feedback


