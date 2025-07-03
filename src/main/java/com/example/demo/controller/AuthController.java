package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AuthController {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("register")
    public String registerForm(Model model) {
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute User user) {
        user.setRole("GUEST");
        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword()); // ma hoa mat khau
        user.setPassword(hashPassword);
        userRepository.save(user);
        return "redirect:/register";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("login")
    public String postLogin(@ModelAttribute User user, HttpSession session) {
        List<User> list = userRepository.findByEmail(user.getEmail());
        if (list.size() > 0) {
            User exisUser = list.get(0);
            // so sanh password
            if (bCryptPasswordEncoder.matches(user.getPassword(), exisUser.getPassword())) {
                session.setAttribute("currentUser",exisUser);
                return "redirect:/";
            }
        }
        return "redirect:/login";
    }
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.setAttribute("currentUser",null);
        return"redirect:/";
    }
}



