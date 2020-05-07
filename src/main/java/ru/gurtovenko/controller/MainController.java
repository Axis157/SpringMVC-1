package ru.gurtovenko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gurtovenko.dao.UserDAO;
import ru.gurtovenko.model.User;
import ru.gurtovenko.util.UserValidator;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class MainController {

    @Autowired
    @Qualifier("jpaUserDAO")
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/raw")
    @ResponseBody
    public String raw(){
        return "Raw data";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userDAO.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model){
        model.addAttribute("user", new User());
        return "/AnimatedLogin";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) throws SQLException {
        userValidator.validate(user, result);
        if(result.hasErrors()){
            return "/AnimatedLogin";
        }
        else{
            userDAO.add(user);
            return "redirect:/users";
        }
    }
}
