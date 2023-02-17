package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        int mealId = meal1.getId();
        Meal meal = service.get(mealId, USER_ID);
        MATCHER.assertMatch(meal, meal1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, NOT_FOUND));
    }

    @Test
    public void delete() {
        service.delete(MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID, NOT_FOUND));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> userMeals = service.getBetweenInclusive(LocalDate.of(2020, 1, 30), LocalDate.of(2020, 1, 30), USER_ID);
        MATCHER.assertMatch(userMeals, meal3, meal2, meal1);
    }

    @Test
    public void getAll() {
        List<Meal> userMeals = service.getAll(USER_ID);
        MATCHER.assertMatch(userMeals, meal7, meal6, meal5, meal4, meal3, meal2, meal1);
    }

    @Test
    public void update() {
        Meal updated = MealTestData.getUpdated();
        service.update(updated, USER_ID);
        MATCHER.assertMatch(service.get(meal1.getId(), USER_ID), getUpdated());
    }

    @Test
    public void updateNotFound() {
        Meal updated = MealTestData.getUpdated();
        assertThrows(NotFoundException.class, () -> service.update(updated, NOT_FOUND));
    }

    @Test
    public void create() {
        Meal created = service.create(MealTestData.getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = MealTestData.getNew();
        newMeal.setId(newId);
        MATCHER.assertMatch(created, newMeal);
        MATCHER.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(null, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                        "throw description", 1000), USER_ID));
    }
}