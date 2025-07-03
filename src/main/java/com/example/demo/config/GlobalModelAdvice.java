package com.example.demo.config;

import com.example.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class GlobalModelAdvice {
    @ModelAttribute
    public void checkLogin(HttpSession session, HttpServletResponse response, Model model) throws IOException {
        User user = (User) session.getAttribute("currentUser");
        // Danh sách route loai tru
        List<String> freeAccess = List.of("/login","/register","/logout","/css","/js");
        // Lay link ma dang vao
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri =request.getRequestURI();
        boolean canAccess = freeAccess.stream().anyMatch(uri::startsWith);
        // Neu session ko co user va trang do phai dang nhap moi duoc vao
        if(user == null && !canAccess){
            response.sendRedirect("/login");
        }

        if(user !=null){
            model.addAttribute("currentUser",user);
        }
    }
}
