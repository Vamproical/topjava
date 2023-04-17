package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserTo;

@Component
public class EmailValidatorExceptionHandler extends CustomValidatorBean {
    private final UserRepository userRepository;

    public EmailValidatorExceptionHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        UserTo userTo = (UserTo) target;
        if (!userTo.getEmail().isEmpty()) {
            User dbUser = userRepository.getByEmail(userTo.getEmail().toLowerCase());
            if (dbUser != null) {
                errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
            }
        }
    }
}
