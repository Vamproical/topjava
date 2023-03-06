package ru.javawebinar.topjava.service.datajpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest {
    @Test
    public void getWithMeals() {
        User adminUser = service.getWithMeals(ADMIN_ID);
        List<Meal> adminMeals = List.of(adminMeal2, adminMeal1);
        USER_MATCHER.assertMatch(adminUser, admin);
        MEAL_MATCHER.assertMatch(adminUser.getMeals(), adminMeals);
    }

    @Test
    public void getWithMealsNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }

    @Test
    public void getWithMealsByUserWithoutMeal() {
        User user = service.getWithMeals(GUEST_ID);
        USER_MATCHER.assertMatch(user, guest);
        Assert.assertEquals(user.getMeals().size(), 0);
    }
}
