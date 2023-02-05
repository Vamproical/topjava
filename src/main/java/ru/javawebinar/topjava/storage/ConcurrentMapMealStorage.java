package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapMealStorage implements Storage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private int count;

    public ConcurrentMapMealStorage() {
        fillMap();
        count = size();
    }

    @Override
    public synchronized Meal save(Meal meal) {
        meal.setId(++count);
        return storage.put(count, meal);
    }

    @Override
    public Meal update(Meal meal) {
        return storage.replace(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public synchronized void delete(int id) {
        storage.remove(id);
        --count;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    private void fillMap() {
        MealsUtil.getMeals()
                .forEach(this::save);
    }
}
