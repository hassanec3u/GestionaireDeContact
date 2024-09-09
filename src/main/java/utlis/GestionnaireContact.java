package utlis;

import vue.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionnaireContact {

    public static void saveContact(Contact contact) throws SQLException {
        String query = "INSERT INTO utilisateur (id, nom, prenom, email,phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, contact.getId());
            statement.setString(2, contact.getFirstName());
            statement.setString(3, contact.getName());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getPhone());
            statement.executeUpdate();
        }
    }

    public static List<Contact> loadContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM utilisateur";
        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("id"),
                        resultSet.getString("prenom"),
                        resultSet.getString("nom"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                );
                contacts.add(contact);
            }
        }
        return contacts;
    }

}
