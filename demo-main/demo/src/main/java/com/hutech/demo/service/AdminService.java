package com.hutech.demo.service;

import com.hutech.demo.model.Role;
import com.hutech.demo.model.User;
import com.hutech.demo.repository.IRoleRepository;
import com.hutech.demo.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final IUserRepository userRepository; // Truy cập vào repository của User
    private final IRoleRepository roleRepository; // Truy cập vào repository của Role

    // Lấy tất cả người dùng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy thông tin người dùng theo id
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Thêm người dùng mới
    public User saveUser(User user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Cập nhật người dùng
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User with ID " + user.getId() + " does not exist."));

        // Cập nhật các trường thông tin người dùng
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles()); // Cập nhật quyền của người dùng

        return userRepository.save(existingUser);
    }

    // Xóa người dùng
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

    // Tìm kiếm người dùng theo tên đăng nhập
    public List<User> searchUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    // Lấy tất cả vai trò
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Lấy danh sách vai trò theo ID
    public Set<Role> getRolesByIds(List<Long> roleIds) {
        return roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
    }

}
