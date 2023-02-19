package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

public class MealTestData {
    public static final Matcher<Meal> MATCHER = Matcher.newInstance();

    public static final int MEAL_ID = UserTestData.GUEST_ID + 1;
    public static final int MEAL_NOT_FOUND_ID = 100;
    public static final int ADMIN_MEAL_ID = MEAL_ID + 7;
    public static final Meal meal1 = new Meal(MEAL_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "user breakfast", 500);
    public static final Meal meal2 = new Meal(MEAL_ID + 1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "user lunch", 1000);
    public static final Meal meal3 = new Meal(MEAL_ID + 2, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "user dinner", 500);
    public static final Meal meal4 = new Meal(MEAL_ID + 3, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "user cheat-mil", 100);
    public static final Meal meal5 = new Meal(MEAL_ID + 4, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "user breakfast", 1000);
    public static final Meal meal6 = new Meal(MEAL_ID + 5, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "user lunch", 500);
    public static final Meal meal7 = new Meal(MEAL_ID + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "user dinner", 410);

    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2020, 1, 1, 10, 0, 0), "someDescription", 100);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setDescription("updated description");
        updated.setDateTime(LocalDateTime.of(2020,1,25,10,0,0));
        updated.setCalories(100);
        return updated;
    }
}
