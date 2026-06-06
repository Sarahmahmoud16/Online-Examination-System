# Online-Examination-System

A secure and scalable backend system for managing online examinations, built using **Spring Boot**.
The system supports **role-based access control** for Managers, Instructors, and Students.

---

##  Features

###  Authentication & Authorization

* User Registration & Login
* JWT-based authentication
* Role-based authorization (Manager / Instructor / Student)

---

##  Manager Features

* Manage users (create / view / deactivate)
* Assign roles (Student / Instructor / Manager)
* Filter users (by role)

---

## Instructor Features

* Create and manage exams
* Add questions:

  * MCQ
  * True / False
  * Essay
* Schedule exams (start & end time)
* Grade subjective (essay) questions
* View student performance

---

##  Student Features

* Register & login
* Enroll in courses
* Take exams
* Submit answers (auto-submit on timeout)
* View results and exam history

---

##  Core System Functionalities

###  Course Management

* Create courses
* Assign instructors
* Enroll students
* View course details

---

###  Exam Management

* Create exams (title, duration, total marks)
* Assign exams to courses
* Publish / unpublish exams

---

###  Question Management

* Multiple question types (MCQ / TF / Essay)
* Store correct answers
* Assign marks per question

---

###  Exam Taking System

* Timer-based exam sessions
* Auto-submit when time ends
* Secure submission flow

---

###  Grading System

* Auto grading (MCQ / True & False)
* Manual grading (Essay by instructor)

---

###  Results & Reports

* Student: view score & answers
* Instructor: class performance
* Manager: system reports (pass rate, averages)

---

##  Tech Stack

* Java
* Spring Boot
* Spring Security
* JWT (JSON Web Token)
* Hibernate / JPA
* MySQL / PostgreSQL
* Maven

---

##  Project Structure

```
src/
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── dto
 ├── exception
 └── security
```

---

##  Sample API Endpoints

###  Auth

* `POST /auth/register`
* `POST /auth/login`

---

###  Manager

* `POST /manager/users`
* `GET /manager/users`
* `GET /manager/users/{id}`
* `PATCH /manager/users/{id}/deactivate`
* `GET /manager/users?role=`

---

###  Instructor

* `POST /instructor/exams`
* `POST /instructor/questions`
* `PATCH /instructor/exams/{id}/publish`
* `GET /instructor/reports`

---

###  Student

* `POST /student/enroll`
* `POST /student/exams/{id}/start`
* `POST /student/exams/{id}/submit`
* `GET /student/results`

---


##  Author

**Sara Mahmoud**
 Backend Developer

---

## ⭐ Star the repo if you like it!
