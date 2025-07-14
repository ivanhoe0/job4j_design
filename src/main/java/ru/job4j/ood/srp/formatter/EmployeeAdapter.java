package ru.job4j.ood.srp.formatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ru.job4j.ood.srp.model.Employee;

import java.io.IOException;

public class EmployeeAdapter extends TypeAdapter<Employee> {

    private ReportDateTimeParser parser = new ReportDateTimeParser();

    @Override
    public void write(JsonWriter jsonWriter, Employee employee) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("name");
        jsonWriter.value(employee.getName());
        jsonWriter.name("hired");
        jsonWriter.value(parser.parse(employee.getHired()));
        jsonWriter.name("fired");
        jsonWriter.value(parser.parse(employee.getFired()));
        jsonWriter.name("salary");
        jsonWriter.value(employee.getSalary());
        jsonWriter.endObject();
    }

    @Override
    public Employee read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
