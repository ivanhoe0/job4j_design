package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.*;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;

    private Gson json;

    public JsonReportEngine(Store store) {
        this.store = store;
        json = new GsonBuilder()
                .registerTypeAdapter(Employee.class, new EmployeeAdapter())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return json.toJson(store.findBy(filter));
    }
}
