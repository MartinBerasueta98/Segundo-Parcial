import Contenedora.SSM;
import Model.Persona;

public class Main {
    public static void main(String[] args) {
        SSM ssm = new SSM();
        ssm.agregarKits(10);
        ssm.ingresarPersona(new Persona("Martin", "Berasueta", "Libertad", "41149230", 26));
        //Repito 1 persona pera verificar funcionamiento de dniExistente();
        ssm.ingresarPersona(new Persona("Martin", "Berasueta", "Libertad", "41149230", 26));
        ssm.ingresarPersona(new Persona("Matias", "Perez", "San Antonio", "39324523", 28));
        ssm.ingresarPersona(new Persona("Fernando", "Sapia", "Las Heras", "18487028", 56));
        ssm.testear();
        ssm.generarJSON();
    }
}