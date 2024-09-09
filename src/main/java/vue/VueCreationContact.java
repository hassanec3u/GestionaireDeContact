package vue;

import utlis.GestionnaireContact;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueCreationContact extends JFrame {

    public VueCreationContact() {
        setTitle("Créer un contact");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        // cree des composants pour le formulaire
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

        // cree un bouton pour soumettre le formulaire
        JButton submitButton = new JButton("Créer");

        // ajoute les composants au formulaire
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
            Contact newContact = new Contact(id, firstName, name, phone, email);

            // sauvegarder le contact dans la base de données
            try {
                GestionnaireContact.saveContact(newContact);
                JOptionPane.showMessageDialog(null, "Contact créé avec succès!");

                // fermer la fenetre
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde du contact: " + ex.getMessage());
            }

        });

        setVisible(true);
    }
}