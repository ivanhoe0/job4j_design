package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;

class HRReportTest {
    @Test
    void whenGeneratedReport() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("John", now, now, 245000);
        Employee worker2 = new Employee("Sarah", now, now, 225000);
        Employee worker3 = new Employee("William", now, now, 200000);
        store.add(worker1);
        store.add(worker3);
        store.add(worker2);
        Comparator<Employee> comparator = (d1, d2) -> {
            return Double.valueOf(d2.getSalary()).compareTo(Double.valueOf(d1.getSalary()));
        };
        Report report = new HRReport(store, parser, comparator);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary()).append(" ")
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary()).append(" ")
                .append(System.lineSeparator());
        assertThat(report.generate(t -> true)).isEqualTo(expected.toString());
    }
}