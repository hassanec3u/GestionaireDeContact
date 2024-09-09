package vue;

import utlis.GestionnaireContact;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VueCreationContact extends JFrame {

    public VueCreationContact() {
        setTitle("Créer un contact");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // Create input fields
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel firstNameLabel = new JLabel("Prénom:");
        JTextField firstNameField = new JTextField();
        JLabel nameLabel = new JLabel("Nom:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Téléphone:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        // Create submit button
        JButton submitButton = new JButton("Créer");

        // Add components to the frame
        add(idLabel);
        add(idField);
        add(firstNameLabel);
        add(firstNameField);
        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
        add(new JLabel()); // creer un espace vide pour aligner le bouton
        add(submitButton);

        // ajouter une action listener pour le bouton
        submitButton.addActionListener(e -> {
            // recupere les valeurs des champs
            String id = idField.getText();
            String firstName = firstNameField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            // creer un nouveau contact avec les valeurs des champs
            List<Contact> contacts = new ArrayList<>();
            Contact newContact = new Contact(id, firstName, name, phone, email);


            //charge les contacts existants et ajouter le nouveau contact
            try {
                contacts = GestionnaireContact.loadContacts("contacts.json");
                contacts.add(newContact);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors du chargement des contacts: " + ex.getMessage());
            }


            // sauvegarder le contact dans un fichier json
            try {
                GestionnaireContact.saveContacts("contacts.json", contacts);
                JOptionPane.showMessageDialog(null, "Contact créé avec succès!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du contact: " + ex.getMessage());
            }
            // fermer la fenetre
            dispose();
        });

        setVisible(true);
    }
}