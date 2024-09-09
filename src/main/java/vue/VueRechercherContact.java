package vue;

import utlis.GestionnaireContact;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VueRechercherContact extends JFrame {

    public VueRechercherContact() {
        setTitle("Rechercher un contact");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel pour le formulaire de recherche
        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        searchPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton searchButton = new JButton("Rechercher");

        searchPanel.add(idLabel);
        searchPanel.add(idField);
        searchPanel.add(new JLabel()); // Espace vide pour aligner le bouton
        searchPanel.add(searchButton);

        // Zone de résultat
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Ajouter les composants au frame
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        // Ajouter une action listener pour le bouton de recherche
        searchButton.addActionListener(e -> {
            String id = idField.getText();
            try {
                List<Contact> contacts = GestionnaireContact.searchContact(id);
                if (contacts.isEmpty()) {
                    resultArea.setText("Aucun contact trouvé");
                } else {
                    StringBuilder resultText = new StringBuilder();
                    for (Contact contact : contacts) {
                        resultText.append("ID: ").append(contact.getId()).append("\n")
                                .append("Prénom: ").append(contact.getFirstName()).append("\n")
                                .append("Nom: ").append(contact.getName()).append("\n")
                                .append("Téléphone: ").append(contact.getPhone()).append("\n")
                                .append("Email: ").append(contact.getEmail()).append("\n\n");
                    }
                    resultArea.setText(resultText.toString());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la recherche du contact: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}