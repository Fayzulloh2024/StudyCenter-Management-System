package org.example.study.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.entity.Lesson;
import org.example.study.entity.StudentAttendance;
import org.example.study.entity.TimeTable;
import org.example.study.entity.enums.LessonStatus;
import org.example.study.repo.LessonRepo;
import org.example.study.repo.StudentAttendanceRepo;
import org.example.study.repo.TimeTableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequiredArgsConstructor
public class StudentAttendanceController {

    private final StudentAttendanceRepo studentAttendanceRepository;
    private final TimeTableRepository timeTableRepository;
    private final LessonRepo lessonRepository;

    @PostMapping("/studentAttendance")
    public String attendance(
            @RequestParam Integer studentAttendanceId,
            @RequestParam Integer timeTableId,
            @RequestParam Integer currentLessonId
    ) {
        Lesson lesson = lessonRepository.findById(currentLessonId).get();
        TimeTable timeTable = timeTableRepository.findById(timeTableId).get();
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(studentAttendanceId).get();
        if (studentAttendance.getLesson().equals(lesson) && lesson.getStatus().equals(LessonStatus.IN_PROGRESS)) {
            studentAttendance.setHasInLesson(!studentAttendance.isHasInLesson());
            studentAttendanceRepository.save(studentAttendance);
        }
        return "redirect:/?groupId=" + timeTable.getGroup().getId() + "&timeTableId=" + timeTable.getId();
    }
}
