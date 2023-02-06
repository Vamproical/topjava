package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealStorage implements MealStorage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger();

    public InMemoryMealStorage() {
        fillStorage();
    }

    private void fillStorage() {
        MealsUtil.getMeals().forEach(this::create);
    }

    @Override
    public Meal create(Meal meal) {
        meal.setId(count.incrementAndGet());
        storage.put(count.get(), meal);
        return meal;
    }

    @Override
    public Meal update(Meal meal) {
        storage.replace(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
