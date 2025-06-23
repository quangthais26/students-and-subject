package com.example.demo.controller;

import com.example.demo.entity.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping()
    public String getAllSubjects(Model model) {
        List<Subject> list = subjectRepository.findAll();
        model.addAttribute("subjects", list);
        model.addAttribute("content", "subjects/list");
        return "layout/main";
    }

    @GetMapping("create")
    public String formCreate(Model model) {
        model.addAttribute("content", "subjects/create_form");
        return "layout/main";
    }

    @PostMapping("create")
    public String createSubject(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("edit/{id}")
    public String editSubject(@PathVariable Long id, Model model) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        model.addAttribute("subject", subject);
        model.addAttribute("content", "subjects/edit_form");
        return "layout/main";
    }

    @PostMapping("edit")
    public String updateSubject(@ModelAttribute Subject subject) {
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @GetMapping("delete/{id}")
    public String deleteSubject(@PathVariable Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow();
        subjectRepository.delete(subject);
        return "redirect:/subjects";
    }
}
