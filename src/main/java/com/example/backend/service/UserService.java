package com.example.backend.service;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.UserRole;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        setRoles(user);
        return user;
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        for(User user : users){
            setRoles(user);
        }
        return users;
    }

    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<User> findByFirstNameLastNameAndPatronymic(String firstName, String lastName, String patronymic) {
        return userRepository.findByFirstNameLastNameAndPatronymic(firstName, lastName, patronymic);
    }

    public User findById(UUID id) {
        return userRepository.findById(id);
    }

    public boolean saveUser(User user){
        return saveUser(user, "USER");
    }

    public boolean saveUser(User user, String role) {
        user.setId(UUID.randomUUID());
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        boolean result = userRepository.saveUser(user);
        if (roleRepository.search(role) != null && result) {
            userRole.setRoleId(roleRepository.search(role).getId());
            userRoleRepository.addRole(userRole);
        }
        setRoles(userRepository.findById(user.getId()));
        return result;
    }

    public boolean addRole(UUID userId, String roleName) {
        if (roleRepository.search(roleName).equals(null)) {
            return false;
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleRepository.search(roleName).getId());
        boolean result = userRoleRepository.addRole(userRole);
        setRoles(userRepository.findById(userId));
        return  result;
    }

    public boolean removeUserRole(UUID id) {
        UserRole userRole = userRoleRepository.search(id);
        boolean result = userRoleRepository.delete(id);
        setRoles(userRepository.findById(userRole.getUserId()));
        return result;
    }

    public boolean update(UUID oldId, User user) {
        return userRepository.update(oldId, user);
    }

    public boolean delete(UUID id) {
        boolean result = userRepository.delete(id);
        setRoles(userRepository.findById(id));
        return result;
    }

    public void setRoles(User user){
        user.setRoles(roleRepository
                .search(userRoleRepository.findByUserId(user.getId())));
    }
}
