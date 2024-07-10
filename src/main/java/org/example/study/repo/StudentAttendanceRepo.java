package org.example.study.repo;


import org.example.study.entity.StudentAttendance;
import org.example.study.entity.TimeTableStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Integer> {
    public List<StudentAttendance> findAllByTimeTableStudentOrderByLessonDateAsc(TimeTableStudent timeTableStudent);


}