package org.example.study.service;


import lombok.RequiredArgsConstructor;
import org.example.study.entity.Lesson;
import org.example.study.entity.StudentAttendance;
import org.example.study.entity.TimeTable;
import org.example.study.entity.TimeTableStudent;
import org.example.study.repo.StudentAttendanceRepo;
import org.example.study.repo.TimeTableStudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentAttendanceService {

    private final StudentAttendanceRepo studentAttendanceRepository;
    private final TimeTableStudentRepository timeTableStudentRepository;

    public HashMap<TimeTableStudent, List<StudentAttendance>> getEachStudentAttendance(List<TimeTableStudent> currentTimeTableStudents) {
        HashMap<TimeTableStudent, List<StudentAttendance>> timeTableStudentListHashMap = new HashMap<>();
        for (TimeTableStudent timeTableStudent : currentTimeTableStudents) {
            List<StudentAttendance> attendances = studentAttendanceRepository.findAllByTimeTableStudentOrderByLessonDateAsc(timeTableStudent);
            timeTableStudentListHashMap.put(timeTableStudent, attendances);
        }
        return timeTableStudentListHashMap;
    }

    public void generateStudentAttendanceEachLessonAndSave(TimeTable timeTable, List<Lesson> lessons) {

        List<TimeTableStudent> timeTableStudents = timeTableStudentRepository.findByTimeTableId(timeTable.getId());

        List<StudentAttendance> timeTableStudentsAttendance = new ArrayList<>();

        lessons.forEach(lesson -> {
            timeTableStudents.forEach(timeTableStudent -> {
                timeTableStudentsAttendance.add(StudentAttendance.builder()
                        .hasInLesson(true)
                        .timeTableStudent(timeTableStudent)
                        .lesson(lesson)
                        .mark(0)
                        .build());
            });
        });

        studentAttendanceRepository.saveAll(timeTableStudentsAttendance);
    }
}
