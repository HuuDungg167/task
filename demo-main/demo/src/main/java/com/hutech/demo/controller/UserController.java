package com.hutech.demo.controller;

import com.hutech.demo.createrequest.CreateUserRequest;
import com.hutech.demo.model.User;
import com.hutech.demo.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

// LOGIN
    @GetMapping("/login")
    public String login() {
        return "users/login";
    }

    @PostMapping("/login")
    public String loginPost(@AuthenticationPrincipal UserDetails currentUser) {
        // Kiểm tra vai trò của người dùng
        if (currentUser.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            // Nếu là admin, chuyển hướng đến trang quản trị
            return "redirect:/admin";
        }
        // Nếu không phải admin, chuyển hướng đến trang mặc định (hoặc trang người dùng)
        return "redirect:/index";
    }

    //REGISTER
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           @NotNull BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "users/register";
        }
        userService.save(user);
        return "redirect:/login";
    }


    @GetMapping("/user-info")
    public String getUserInfo(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByUsername(currentUser.getUsername());
        model.addAttribute("user", user);
        return "users/user-info";
    }

}
