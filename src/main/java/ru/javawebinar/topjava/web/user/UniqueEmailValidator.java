package ru.javawebinar.topjava.web.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.web.ExceptionInfoHandler;

import java.util.Objects;

@Component
public class UniqueEmailValidator extends CustomValidatorBean {
    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.isAssignableFrom(clazz) || User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        super.validate(target, errors);
        String email;
        Integer id;
        if (target instanceof User) {
            email = ((User) target).getEmail();
            id = ((User) target).getId();
        } else {
            email = ((UserTo) target).getEmail();
            id = ((UserTo) target).getId();
        }

        if (email == null || email.isEmpty()) return;
        User checkUser = userRepository.getByEmail(email);
        if (checkUser != null && !Objects.equals(checkUser.getId(), id)) {
            errors.rejectValue("email", ExceptionInfoHandler.EXCEPTION_DUPLICATE_EMAIL);
        }
    }
}
