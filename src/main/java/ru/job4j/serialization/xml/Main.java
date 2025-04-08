package ru.job4j.serialization.xml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import ru.job4j.serialization.json.Department;
import ru.job4j.serialization.json.Division;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Department department = new Department(true, 35, "Отдел продаж", new Division("Отдел продаж г.Москва"),
                new String[] {"employee1", "employee2"}
        );
        JAXBContext context = JAXBContext.newInstance(Department.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(department, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Department departmentDeserialized = (Department) unmarshaller.unmarshal(reader);
            System.out.println(departmentDeserialized);
        }
    }
}
