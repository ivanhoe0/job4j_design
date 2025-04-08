package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Division")
public class Division {
    @XmlAttribute
    private String title;

    public Division() { }

    public Division(String title) {
        this.title = title;
    }
}
