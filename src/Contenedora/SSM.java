package Contenedora;

import Excepciones.KitNoDisponibleException;
import Excepciones.TemperaturaSuperiorException;
import Model.Persona;
import Model.Reactivo;
import Utilidades.FileManagement;
import Utilidades.JsonUtilidades;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SSM {
    private ArrayList<Persona> personas;
    private ArrayList<Reactivo> cantReactivos;
    private HashMap<Integer, StringBuilder> testeados;

    public SSM() {
        personas = new ArrayList<>();
        cantReactivos = new ArrayList<>();
        testeados = new HashMap<>();
    }

    public void consultarStockKits(){
        try {
            if (!cantReactivos.isEmpty()) {
                //Si tengo Stock lo muestro
                System.out.println("Cantidad de reactivos restantes:" + (cantReactivos.size()));
            }else {
                //Si no lanzo excepcion.
                throw new KitNoDisponibleException("No hay Kits Disponibles");
            }
        }catch (KitNoDisponibleException e){
            //Como tratamiento preguntamos si quieren agregar y cuantos.
            Scanner scanner = new Scanner(System.in);
            System.out.println("No hay reactivos disponibles, desea agregar mas? 1) Si 2) No");
            int rta = scanner.nextInt();

            if (rta == 1) {
                //Agregamos la cantidad que se nos pase por teclado
                System.out.println("Ingrese la cantidad de Kits a ingresar: ");
                Integer cant = scanner.nextInt();
                cantReactivos = agregarKits(cant);
            }
        }
    }

    public ArrayList<Reactivo>agregarKits(Integer cant){
        ArrayList<Reactivo> nuevosKits = new ArrayList<>();

        if (cant!=null){
            for (int i=0; i<cant; i++){
                Reactivo reactivo = new Reactivo();
                reactivo.setId(i);
                reactivo.setTemperaturaRegistrada(0F);
                nuevosKits.add(reactivo);
            }
        }
        return nuevosKits;
    }

    public boolean dniExistente(String nuevoIngresado){
        int i=0;
        while (i<personas.size()){
            if (personas.get(i).getDni().equalsIgnoreCase(nuevoIngresado)){
                System.out.println("Persona Repetida, no se agrego a la persona con DNI: " + nuevoIngresado);
                return true;
            }
            i++;
        }
        return false;
    }

    public void ingresarPersona(Persona p) {
        if (!dniExistente(p.getDni())){
            Scanner scanner = new Scanner(System.in);
            consultarStockKits();
            p.setKit(cantReactivos.removeFirst());
            System.out.println("Ingrese Ocupacion de la persona: ");
            p.setOcupacion(scanner.nextLine());

            personas.addLast(p);
        }
    }

    public void testear() {
        Random random = new Random();
        Float tempRegistada = null;
        for (Persona persona : personas) {
            do {
                //Genero un numero random menor a 40
                tempRegistada = random.nextFloat(40);
            } while (tempRegistada < 36);//Obligo a que quede en bucle hasta que la temperatura sea mayor a 36
            persona.getKit().setTemperaturaRegistrada(tempRegistada);//Asigno la temperatura a al kit de la persona
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(persona.getDni()).append(", ").append(persona.getKit().getTemperaturaRegistrada());//Genero un StringBuilder con DNI y la temperatura de la persona
            testeados.put(persona.getKit().getId(), stringBuilder);//Almaceno la información del kit y de la persona en un HASHMAP
        }
        aislar();
    }

    public void aislar() {
        for (Persona persona : personas) {
            try {
                if (persona.getKit().getTemperaturaRegistrada() >= 38) {

                    throw new TemperaturaSuperiorException("Barrio y  Nro de Test guardados");//Señal de mensaje que se ha lanzado la excepcion
                }
            } catch (TemperaturaSuperiorException e) {//Si llegaramos a entrar en la excepcion obtenemos el Barrio y el número de Kit y lo almacenamos en un archivo .dat
                e.getP().setBarrio(persona.getBarrio());
                e.getP().setNroKit(persona.getKit().getId());
                FileManagement.writeFile(e.getP());
                System.out.println(e.getMessage());
            }
        }
    }

    public void generarJSON(){
        JSONArray sanos = new JSONArray();
        JSONArray aislar = new JSONArray();//Creo los JSONArrays

        for (Persona persona : personas) {
            if (persona.getKit().getTemperaturaRegistrada() < 38) {
                JSONObject jsonObject = new JSONObject();//Creo el JSONObject y les asigno las etiquetas y sus valores correspondientes
                jsonObject.put("nombre",persona.getNombre());
                jsonObject.put("apellido",persona.getApellido());
                jsonObject.put("dni",persona.getDni());
                jsonObject.put("edad",persona.getEdad());
                jsonObject.put("ocupacion",persona.getOcupacion());
                jsonObject.put("barrio",persona.getBarrio());
                sanos.put(jsonObject);
            } else {
                JSONObject jsonObject = new JSONObject();//Creo el JSONObject y les asigno la informacion del Kit y del Barrio de la persona a aislar
                jsonObject.put("id", persona.getKit().getId());
                jsonObject.put("temperatura", persona.getKit().getTemperaturaRegistrada());
                jsonObject.put("barrio", persona.getBarrio());
                aislar.put(jsonObject);
            }
        }
        JSONObject JSONPersonas = new JSONObject();
        JSONPersonas.put("sanos", sanos);
        JSONPersonas.put("aislar", aislar);
        JsonUtilidades.grabar(JSONPersonas, "archivo");
    }

}
