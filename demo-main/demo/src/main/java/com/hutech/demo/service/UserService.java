package com.hutech.demo.service;

import com.google.firebase.remoteconfig.internal.TemplateResponse;
import com.hutech.demo.Roles;
import com.hutech.demo.createrequest.CreateUserRequest;
import com.hutech.demo.mapper.UserMapper;
import com.hutech.demo.model.Customer;
import com.hutech.demo.model.User;
import com.hutech.demo.repository.CustomerRepository;
import com.hutech.demo.repository.IRoleRepository;
import com.hutech.demo.repository.IUserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final UserMapper userMapper;
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }

    public void save(@NotNull User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public void setDefaultRole(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.getRoles().add(roleRepository.findRoleById(Roles.CUSTOMER.value));
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(@NotNull User user) {
        User existingUser = getUserById(user.getId())
                .orElseThrow(() -> new IllegalStateException("User with ID " +
                        user.getId() + " does not exist."));
        // Update fields of existing user
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("User with ID " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }
    public User createUser(CreateUserRequest request){
        User user = userMapper.toUser(request);
        userRepository.save(user);
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setAddress(user.getAddress());
        customerRepository.save(customer);
        return user;
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


}
