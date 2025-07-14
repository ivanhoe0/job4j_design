package ru.job4j.ood.srp.report;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import ru.job4j.ood.srp.model.Emploees;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private Store store;
    private Marshaller marshaller;

    public XmlReportEngine(Store store) {
        this.store = store;
        try {
            JAXBContext context = JAXBContext.newInstance(Emploees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var result = "";
        Emploees emploees = new Emploees(store.findBy(filter));
            try (StringWriter stringWriter = new StringWriter()) {
                marshaller.marshal(emploees, stringWriter);
                result = stringWriter.getBuffer().toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        return  result;
    }
}
