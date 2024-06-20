package Utilidades;

import Model.PersonaAislada;

import java.io.*;

public class FileManagement {
    private static final String FILE_PATH = "urgente.dat";

    public static PersonaAislada readFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            // Si el archivo no existe, creamos un objeto PersonaAislada vacía y lo guardamos
            PersonaAislada personaAislada = new PersonaAislada();
            writeFile(personaAislada);
            return personaAislada;
        }
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            // Si el archivo existe, lo leemos y devolvemos el objeto PersonaAislada almacenado
            return (PersonaAislada) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeFile(PersonaAislada personaAislada) {
        File file = new File(FILE_PATH);

        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            // Guardamos el objeto Cinema en el archivo
            objectOutputStream.writeObject(personaAislada);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}