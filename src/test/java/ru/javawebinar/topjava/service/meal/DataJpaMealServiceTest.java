package ru.javawebinar.topjava.service.meal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest {
    @Test
    public void getWithUser() {
        Meal meal = service.getWithUser(MEAL1_ID, UserTestData.USER_ID);
        MEAL_MATCHER.assertMatch(meal, meal1);
        USER_MATCHER.assertMatch(meal.getUser(), UserTestData.user);
    }

    @Test
    public void getWithUserNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.getWithUser(NOT_FOUND, UserTestData.USER_ID));
    }
}
