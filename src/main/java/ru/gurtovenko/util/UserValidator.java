package ru.gurtovenko.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.gurtovenko.dao.UserDAO;
import ru.gurtovenko.model.User;

@Component
public class UserValidator implements Validator {

    @Autowired
    @Qualifier("jdbcTemplateUserDAO")
    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(userDAO.getOne(user.getEmail()) != null){
            errors.rejectValue("email","","This email is already in use");
        }
    }
}
