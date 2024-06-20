package Model;

public class Reactivo {
    private Integer id;
    private Float temperaturaRegistrada;

    public Reactivo() {
    }

    public Integer getId() {
        return id;
    }

    public Float getTemperaturaRegistrada() {
        return temperaturaRegistrada;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTemperaturaRegistrada(Float temperaturaRegistrada) {
        this.temperaturaRegistrada = temperaturaRegistrada;
    }
}
