package org.example.study.controller;


import lombok.RequiredArgsConstructor;
import org.example.study.entity.Group;
import org.example.study.repo.GroupRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupRepo groupRepository;

    @GetMapping()
    public String groups() {
        return "addGroup";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name) {
        Group group = Group.builder()
                .name(name)
                .build();
        groupRepository.save(group);
        return "redirect:/";
    }



}
