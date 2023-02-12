package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);

            System.out.println(mealRestController.get(1));
            //System.out.println(mealRestController.get(8));
            System.out.println(mealRestController.getAll());
            System.out.println(mealRestController.getAllFiltered(null, null, null, null));
            System.out.println(mealRestController.getAllFiltered(LocalDate.of(2020, 1, 30),
                    LocalDate.of(2020, 1, 30),
                    LocalTime.of(5, 0, 0),
                    LocalTime.of(14, 0, 0)));
            Meal updatedMeal = new Meal(2, LocalDateTime.now(), "SomeText", 100);
            updatedMeal.setUserId(1);
            mealRestController.update(updatedMeal, 2);
            //mealRestController.update(mealRestController.get(1), 2);
            mealRestController.delete(2);
            System.out.println(mealRestController.getAll());
        }
    }
}
