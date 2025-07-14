package ru.job4j.ood.srp.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
@XmlRootElement(name = "employees")
public class Emploees {
    @XmlElement(name = "employee")
    private List<Employee> list;

    public Emploees(List<Employee> list) {
        this.list = list;
    }

    public Emploees() { }
}
