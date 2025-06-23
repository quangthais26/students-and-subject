package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/students")
public class StundentController {
        private StudentRepository studentRepository;

    public StundentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

@GetMapping()
public String getAllStudent(Model model){
        List<Student> list = studentRepository.findAll();
        model.addAttribute("students",list);
        model.addAttribute("content","students/list");
        return "layout/main";
}

    @GetMapping("create")
    public String formCreate(Model model){
        model.addAttribute("content","students/create_form");
        return "layout/main";
    }

    @PostMapping("create")
    public String createStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/students";
    }
    @GetMapping("edit/{id}")
    public String editStudent(@PathVariable Long id, Model model){
        Student s = studentRepository.findById(id)
                .orElseThrow();
        model.addAttribute("student",s);
        model.addAttribute("content","students/edit_form");
        return "layout/main";
    }
    @PostMapping("edit")
    public String updateStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/students";
    }
    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable Long id){
        Student s = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(s);
        return "redirect:/students";
    }
    }
