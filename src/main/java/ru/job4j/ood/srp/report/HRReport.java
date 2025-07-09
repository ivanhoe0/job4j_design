package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Comparator<Employee> salaryComparator;

    public HRReport(Store store, DateTimeParser<Calendar> dateTimeParser, Comparator<Employee> salaryComparator) {
        this.store = store;
        this.salaryComparator = salaryComparator;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee> employees = new ArrayList<>(store.findBy(filter));
        employees.sort(salaryComparator);
        for (var e : employees) {
            text.append(e.getName()).append(" ")
                    .append(e.getSalary()).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
