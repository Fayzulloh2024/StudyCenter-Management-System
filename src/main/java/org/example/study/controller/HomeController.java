package org.example.study.controller;

import lombok.RequiredArgsConstructor;
import org.example.study.entity.*;
import org.example.study.entity.enums.TimeTableStatus;
import org.example.study.repo.GroupRepo;
import org.example.study.repo.TimeTableRepository;
import org.example.study.repo.TimeTableStudentRepository;
import org.example.study.service.LessonService;
import org.example.study.service.StudentAttendanceService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final GroupRepo groupRepository;
    private final TimeTableRepository timeTableRepository;
    private final TimeTableStudentRepository timeTableStudentRepository;
    private final StudentAttendanceService studentAttendanceService;
    private final LessonService lessonService;

    @GetMapping("/")
    public String home(
            @AuthenticationPrincipal User user,
            Model model,
            @RequestParam(required = false) Integer groupId,
            @RequestParam(required = false) Integer timeTableId
    ) {
        model.addAttribute("groups", groupRepository.findAll());
        if (groupId != null) {
            model.addAttribute("currentGroup", groupRepository.findById(groupId).get());
            model.addAttribute("timeTables", timeTableRepository.findAllByGroupId(groupId));
        }
        if (Objects.equals(user.getUsername(),"admin")) {
            model.addAttribute("user", user);
        }
        if (timeTableId != null) {
            TimeTable currentTimeTable = timeTableRepository.findById(timeTableId).get();
            List<TimeTableStudent> currentTimeTableStudents = timeTableStudentRepository.findByTimeTableId(timeTableId);
            model.addAttribute("currentTimeTable", currentTimeTable);
            model.addAttribute("currentTimeTableStudents", currentTimeTableStudents);
            if (currentTimeTable.getStatus().equals(TimeTableStatus.IN_PROGRESS) || currentTimeTable.getStatus().equals(TimeTableStatus.COMPLETED)) {
                HashMap<TimeTableStudent, List<StudentAttendance>> eachStudentAttendance = studentAttendanceService.getEachStudentAttendance(currentTimeTableStudents);
                Lesson currentLesson = lessonService.getCurrentLessonByTimeTableId(timeTableId);
                if (currentLesson == null && currentTimeTable.getStatus() != TimeTableStatus.COMPLETED) {
                    currentTimeTable.setStatus(TimeTableStatus.COMPLETED);
                    timeTableRepository.save(currentTimeTable);
                } else {
                    model.addAttribute("currentLesson", currentLesson);
                }
                model.addAttribute("eachStudentAttendance", eachStudentAttendance);
            }
        }
        return "home";
    }
}
