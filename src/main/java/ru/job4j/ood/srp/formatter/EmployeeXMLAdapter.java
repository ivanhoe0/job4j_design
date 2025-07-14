package ru.job4j.ood.srp.formatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.Calendar;

public class EmployeeXMLAdapter extends XmlAdapter<String, Calendar> {
    private ReportDateTimeParser parser = new ReportDateTimeParser();

    @Override
    public Calendar unmarshal(String s) throws Exception {
        return null;
    }

    @Override
    public String marshal(Calendar calendar) throws Exception {
        return parser.parse(calendar);
    }
}
