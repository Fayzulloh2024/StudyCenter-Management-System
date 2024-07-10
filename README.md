# StudyCenter Management System

## Overview
The StudyCenter Management System is a web application designed to manage educational institutions' operations, including group management, timetable scheduling, and attendance tracking. Built with Thymeleaf and Spring MVC, it offers secure access for both teachers and administrators.

## Features

### Teacher Functionality
- **Group Overview**: Upon login, teachers can see all the groups they are assigned to.
- **Attendance Management**: Teachers can start a lesson and manage student attendance. Once the lesson is ended, attendance cannot be modified.
- **Current Lesson Tracking**: Teachers can only manage attendance for the current lesson after starting it.

### Admin Functionality
- **Group Management**: Admins can create and manage groups.
- **Timetable Management**: Admins can create timetables and assign students to these timetables.
- **Teacher Assignment**: Once a timetable is started, a teacher can be assigned to it, and the assigned teacher can view the timetable and associated students.

## Technologies Used
- **Backend**: Spring MVC
- **Frontend**: Thymeleaf, JavaScript
- **Security**: Implemented to ensure secure login and role-based access control

## How to Run
1. Clone the repository.
2. Configure the database settings in `application.properties`.
3. Run the backend server.
4. Access the application via a web browser.

## Contact
For any inquiries, please contact [your email here].
