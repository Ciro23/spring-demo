package it.tino.springdemo.user.controller;

import it.tino.springdemo.user.User;
import it.tino.springdemo.user.persistence.UserDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserController implements WebMvcConfigurer {

    private final UserDataSource userDataSource;

    public UserController(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @GetMapping
    public String showAllUsers(Model model) {
        List<User> allUsers = userDataSource.findAll();
        model.addAttribute("users", allUsers);

        return "users";
    }
}
