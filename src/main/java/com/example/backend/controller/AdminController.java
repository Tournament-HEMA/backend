package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        final boolean created = userService.saveUser(user);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/registrationNewAdmin")
    public ResponseEntity<?> addUser(@RequestBody User user,
                                     @RequestParam(value = "role") String role) {
        final boolean created = userService.saveUser(user, role);
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/giveRights")
    public ResponseEntity<?> addRoles(@RequestParam(value = "userId") UUID userId,
                                      @RequestParam(value = "roleName") String roleName) {
        final boolean added = userService.addRole(userId, roleName);
        return added
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.findAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByFirstName")
    public ResponseEntity<List<User>> findByFirstName(@RequestParam(value = "firstName") String firstName) {
        final List<User> users = userService.findByFirstName(firstName);

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByFirstNameAndLastName")
    public ResponseEntity<List<User>> findByFirstNameAndLastName(@RequestParam(value = "firstName") String firstName,
                                                                 @RequestParam(value = "lastName") String lastName) {
        final List<User> users = userService.findByFirstNameAndLastName(firstName, lastName);

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findByFirstNameLastNameAndPatronymic")
    public ResponseEntity<List<User>> findByFirstNameLastNameAndPatronymic(@RequestParam(value = "firstName") String firstName,
                                                                           @RequestParam(value = "lastName") String lastName,
                                                                           @RequestParam(value = "patronymic") String patronymic) {
        final List<User> users = userService.findByFirstNameLastNameAndPatronymic(firstName, lastName, patronymic);

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<User> findById(@RequestParam(value = "id") UUID id) {
        final User user = userService.findById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/modifyUser")
    public ResponseEntity<?> modifyUser(@RequestParam(value = "oldId") UUID oldId,
                                        @RequestBody User newUser) {
        final boolean updated = userService.update(oldId, newUser);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/removeUser")
    public ResponseEntity<?> removeUser(@RequestParam(value = "id") UUID id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/takeRights")
    public ResponseEntity<?> removeUserRole(@RequestParam(value = "roleId") UUID roleId) {
        final boolean deleted = userService.removeUserRole(roleId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
