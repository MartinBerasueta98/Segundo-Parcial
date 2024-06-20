package Utilidades;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtilidades {
    //Creo un archivo JSON para grabar JSONArrays
    public static void grabar(JSONArray array, String archivo) {
        try {
            FileWriter file = new FileWriter(archivo+".json");
            file.write(array.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Creo un archivo para grabar JSONObjects
    public static void grabar(JSONObject jsonObject, String archivo) {
        try {
            FileWriter file = new FileWriter(archivo+".json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Utilizada para leer un archivo .JSON
    public static String leer(String archivo)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(archivo+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }
}
