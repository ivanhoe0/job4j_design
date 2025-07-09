package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

public class AccountingReport implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Currency currency;

    private final CurrencyConverter converter = new InMemoryCurrencyConverter();

    public AccountingReport(Store store, DateTimeParser<Calendar> dateTimeParser, Currency currency) {
        this.store = store;
        this.currency = currency;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (var e : store.findBy(filter)) {
            text.append(e.getName()).append(" ")
                    .append(dateTimeParser.parse(e.getHired())).append(" ")
                    .append(dateTimeParser.parse(e.getFired())).append(" ")
                    .append(converter.convert(Currency.RUB, e.getSalary(), currency)).append(" ")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
