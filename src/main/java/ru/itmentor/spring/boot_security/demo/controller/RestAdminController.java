package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.ExeptionHandling.NoSuchUserException;
import ru.itmentor.spring.boot_security.demo.ExeptionHandling.UserIncorrectData;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RestAdminController {
    private UserService userService;
    @Autowired
    public RestAdminController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers (){
        List<User> allUsers = userService.findAll();
        return allUsers;
    }
    @GetMapping("/users/{id}")
    public Optional<User> getUserByID (@PathVariable long id){
    Optional<User> user = userService.findById(id);
    if (user.isEmpty()){
        throw new NoSuchUserException("Пользователя с ID - " + id +" не существует в базе данных :(");
    }
    return user;
    }
    @PostMapping("/users")
    public User addNewUser (@RequestBody User user) {
        userService.save(user);
        return user;
    }
    @PutMapping("/users/{id}")
    public User updateUser (@RequestBody User user){
        userService.updateUser(user);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/user/{id}")
    public String deleteUser (@PathVariable Long id){
        userService.deleteById(id);
        return "Пользователь с ID - " + id + "успешно удален";
    }


}
