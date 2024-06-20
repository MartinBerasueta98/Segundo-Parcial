package Model;

import java.io.Serializable;

public class PersonaAislada implements Serializable {
    private String barrio;
    private Integer nroKit;

    public PersonaAislada() {
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setNroKit(Integer nroKit) {
        this.nroKit = nroKit;
    }
}
