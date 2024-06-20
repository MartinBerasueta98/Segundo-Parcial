package Excepciones;

import Model.PersonaAislada;
import jdk.jfr.Percentage;

import java.io.Serializable;

public class TemperaturaSuperiorException extends Exception implements Serializable {
    private PersonaAislada p;
    public TemperaturaSuperiorException(String message) {
        super(message);
        p = new PersonaAislada();
    }

    public PersonaAislada getP() {
        return p;
    }
}
