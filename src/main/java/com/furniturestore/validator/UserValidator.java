package com.furniturestore.validator;

import com.furniturestore.entity.User;
import com.furniturestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    private final Pattern EMAIL_REG =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final Pattern NAME_REG =
            Pattern.compile("^[A-Za-z_]\\w{2,30}$", Pattern.CASE_INSENSITIVE);

    private final Pattern PASSWORD_REG =
            Pattern.compile("^[A-Z0-9a-z_]\\w{3,20}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if (user.getEmail().length() == 0) {
            rejectIfEmptyOrWhitespace(errors, "email", "", "Cant`n be empty.");
        } else if (!EMAIL_REG.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "", "Please enter the correct email.");
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "Already exist.");
        }

        if (user.getPassword().length() == 0) {
            rejectIfEmptyOrWhitespace(errors, "password", "", "Cant`t be empty.");
        } else if (!PASSWORD_REG.matcher(user.getPassword()).matches()) {
            errors.rejectValue("password", "", "Please enter the valid password.");
        }

        if (user.getName().length() == 0) {
            rejectIfEmptyOrWhitespace(errors, "name", "", "Cant`t be empty.");
        } else if (!NAME_REG.matcher(user.getName()).matches()) {
            errors.rejectValue("name", "", "Please enter the valid name.");
        }

        if (user.getLastName().length() == 0) {
            rejectIfEmptyOrWhitespace(errors, "lastName", "", "Cant`t be empty.");
        } else if (!NAME_REG.matcher(user.getLastName()).matches()) {
            errors.rejectValue("lastName", "", "Please enter the valid last name.");
        }
    }
}
