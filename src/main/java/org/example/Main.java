package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static final String FILE_PATH = "personas.json";

    public static void main(String[] args) {

        Persona persona1 = new Persona("Juan", "Perez", 30);
        Persona persona2 = new Persona("Maria", "Lopez", 25);
        Persona persona3 = new Persona("Gabriel", "Jaramillo", 31);
        Persona persona4 = new Persona("Julian", "Gomez", 31);

        agregarPersona(persona1);
        agregarPersona(persona2);
        agregarPersona(persona3);
//        agregarPersona(persona4);
    }

    // Metodo para agregar una persona al archivo JSON si el ID no existe
    public static void agregarPersona(Persona persona) {
        try {
            JSONArray personasArray = leerArchivoJSON();

            // Verificar si el ID ya existe
            if (!idExiste(personasArray, persona.getId())) {
                JSONObject nuevaPersona = new JSONObject();

                // Asignar los valores de la nueva persona
                nuevaPersona.put("id", persona.getId());
                nuevaPersona.put("nombre", persona.getNombre());
                nuevaPersona.put("apellido", persona.getApellido());
                nuevaPersona.put("edad", persona.getEdad());

                personasArray.put(nuevaPersona);
                escribirArchivoJSON(personasArray);
                System.out.println("Persona agregada: " + persona.getNombre());
            } else {
                System.out.println("El ID " + persona.getId() + " ya existe. No se agrega la persona.");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Metodo para verificar si el ID de una persona ya existe en el JSON
    private static boolean idExiste(JSONArray personasArray, int id) {
        for (int i = 0; i < personasArray.length(); i++) {
            JSONObject persona = personasArray.getJSONObject(i);
            if (persona.getInt("id") == id) {
                return true;
            }
        }
        return false;
    }

    // Metodo para leer el archivo JSON, crea un nuevo JSONArray si el archivo no existe
    private static JSONArray leerArchivoJSON() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            return new JSONArray(content);
        } else {
            return new JSONArray();
        }
    }

    // Metodo para escribir el archivo JSON
    private static void escribirArchivoJSON(JSONArray personasArray) throws IOException {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(personasArray.toString(4)); // 4 espacios para formato legible
        }
    }
}

