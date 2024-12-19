package com.hutech.demo.controller;

import com.hutech.demo.model.Role;
import com.hutech.demo.model.User;
import com.hutech.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService; // Sử dụng AdminService để quản lý người dùng

    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard";
    }

    // Hiển thị danh sách người dùng
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", adminService.getAllUsers());
        return "admin/users"; // Trang danh sách người dùng
    }

    // Hiển thị form thêm người dùng
    @GetMapping("/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "admin/add-user"; // Trang thêm người dùng
    }

    // Xử lý thêm người dùng
    @PostMapping("/users/add")
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/add-user";
        }
        adminService.saveUser(user);
        return "redirect:/admin/users";
    }

    // Hiển thị form chỉnh sửa người dùng và vai trò
    @GetMapping("/users/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        User user = adminService.getUserById(id)
                .orElseThrow(() -> new IllegalStateException("User with ID " + id + " does not exist."));

        List<Role> allRoles = adminService.getAllRoles(); // Lấy danh sách tất cả vai trò
        model.addAttribute("allRoles", allRoles); // Truyền danh sách vai trò sang view
        model.addAttribute("user", user);

        return "admin/edit-user"; // Trang chỉnh sửa người dùng
    }

    // Xử lý chỉnh sửa người dùng và cập nhật vai trò
    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user,
                           @RequestParam("roles") List<Long> roleIds, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/edit-user";
        }

        // Lấy danh sách vai trò theo ID từ form
        Set<Role> roles = adminService.getRolesByIds(roleIds);
        user.setRoles(roles);
        user.setId(id);

        adminService.updateUser(user);
        return "redirect:/admin/users";
    }

    // Xử lý xóa người dùng
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
