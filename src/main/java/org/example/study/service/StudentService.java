package org.example.study.service;

import lombok.RequiredArgsConstructor;
import org.example.study.dto.StudentReq;
import org.example.study.entity.Student;
import org.example.study.entity.TimeTable;
import org.example.study.entity.TimeTableStudent;
import org.example.study.repo.StudentRepo;
import org.example.study.repo.TimeTableRepository;
import org.example.study.repo.TimeTableStudentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final TimeTableRepository timeTableRepository;
    private final TimeTableStudentRepository timeTableStudentRepository;
    private final StudentRepo studentRepository;

    public void save(StudentReq studentReq, Integer timeTableId) {

        TimeTable timeTable = timeTableRepository.findById(timeTableId).get();

        Student student = Student.builder()
                .firstName(studentReq.firstName())
                .lastName(studentReq.lastName())
                .phone(studentReq.phone())
                .build();
        studentRepository.save(student);

        TimeTableStudent tableStudent = TimeTableStudent.builder()
                .student(student)
                .timeTable(timeTable)
                .build();
        timeTableStudentRepository.save(tableStudent);

    }
}
