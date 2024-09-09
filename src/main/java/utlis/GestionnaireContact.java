package utlis;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import vue.Contact;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public  class GestionnaireContact {
    /**
     * Sauvegarder les contacts dans un fichier JSON
     * @param filePath Chemin du fichier
     * @param contacts Liste des contacts
     * @throws IOException
     */
    public static void saveContacts(String filePath, List<Contact> contacts) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(contacts, writer);
        }
    }

    /**
     * Charger les contacts depuis un fichier JSON
     * @param filePath Chemin du fichier
     * @return Liste des contacts
     * @throws IOException
     */
    public static List<Contact> loadContacts(String filePath) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Contact>>(){}.getType();
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonArray()) {
                return gson.fromJson(jsonElement, listType);
            } else {
                throw new IOException("Le fichier JSON ne contient pas un tableau d'objets Contact.");
            }
        }
    }

    /**
     * Convertir l'objet en JSON
     * @return JSON string
     */
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
