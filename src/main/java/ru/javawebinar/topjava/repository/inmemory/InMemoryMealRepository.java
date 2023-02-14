package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.userMeals.forEach(meal -> save(meal, 1));
        MealsUtil.adminMeals.forEach(meal -> save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {} with user {}", meal, userId);
        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, map -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {} with user {}", id, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {} with user {}", id, userId);
        Map<Integer, Meal> meals = repository.get(userId);
        return meals != null ? meals.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll user {}", userId);
        return filter(userId, meal -> true);
    }

    @Override
    public List<Meal> getAllFiltered(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        log.info("getAllFiltered startDate {} endDate {} for user {}", startDate, endDate, userId);
        return filter(userId, meal -> DateTimeUtil.isBetweenInclusive(meal.getDateTime(), startDate, endDate));
    }

    private List<Meal> filter(int userId, Predicate<Meal> filter) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? Collections.emptyList() :
                meals.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                        .collect(Collectors.toList());
    }
}

