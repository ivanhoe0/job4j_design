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

class ProgrammersReportTest {
    @Test
    void whenGenerated() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("John", now, now, 245000);
        Employee worker2 = new Employee("Sarah", now, now, 225000);
        store.add(worker1);
        store.add(worker2);
        Report report = new ProgrammersReport(store, parser);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append("; ")
                .append(parser.parse(worker1.getHired())).append("; ")
                .append(parser.parse(worker1.getFired())).append("; ")
                .append(worker1.getSalary()).append("; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append("; ")
                .append(parser.parse(worker2.getHired())).append("; ")
                .append(parser.parse(worker2.getFired())).append("; ")
                .append(worker2.getSalary()).append("; ")
                .append(System.lineSeparator());
        assertThat(report.generate(t -> true)).isEqualTo(expected.toString());
    }
}