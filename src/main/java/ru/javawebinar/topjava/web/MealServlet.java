package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.ConcurrentMapMealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final int MAX_CALORIES = 2000;
    private static final String ADD_OR_EDIT = "/WEB-INF/jsp/edit.jsp";
    private static final String LIST_USER = "/WEB-INF/jsp/meals.jsp";
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = new ConcurrentMapMealStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                executeDelete(request, response);
                break;
            case "add":
                executeAdd(request, response);
                break;
            case "edit":
                executeEdit(request, response);
                break;
            default:
                List<MealTo> mealTos = MealsUtil.filteredByStreams(storage.getAll(), LocalTime.MIN, LocalTime.MAX, MAX_CALORIES);
                request.setAttribute("meals", mealTos);
                request.getRequestDispatcher(LIST_USER).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        Meal meal = new Meal(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            log.debug("adding new meal {}", meal);
            storage.save(meal);
        } else {
            log.debug("updating meal {}", meal);
            meal.setId(Integer.parseInt(id));
            storage.update(meal);
        }

        response.sendRedirect("meals");
    }

    private void executeDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("delete meal");
        int id = Integer.parseInt(request.getParameter("id"));
        storage.delete(id);
        response.sendRedirect("meals");
    }

    private void executeAdd(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("add new meal");
        request.getRequestDispatcher(ADD_OR_EDIT).forward(request, response);
    }

    private void executeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("update meal");
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("meal", storage.get(id));
        request.getRequestDispatcher(ADD_OR_EDIT).forward(request, response);
    }
}
