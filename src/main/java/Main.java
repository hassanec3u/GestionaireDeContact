import utlis.GestionnaireContact;
import vue.Contact;
import vue.VueCreationContact;
import vue.vueListeContact;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Crée la fenêtre principale de l'application
        JFrame frame = new JFrame("Gestionnaire de contacts");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 1000);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // Utilise BoxLayout pour alignement vertical

        // Crée un conteneur Box pour centrer les boutons horizontalement
        Box box = Box.createVerticalBox();

        // Crée un nouveau bouton de création de contact
        JButton createContactButton = new JButton("Créer un contact");
        createContactButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        createContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VueCreationContact();
            }
        });
        box.add(createContactButton);

        // Ajoute un espace entre les boutons
        box.add(Box.createVerticalStrut(10));

        // Crée un nouveau bouton pour afficher les contacts
        JButton showContactsButton = new JButton("Afficher les contacts");
        showContactsButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        showContactsButton.addActionListener(e -> {
           // recupere tout les contacts depuis un fichier JSON
            List contacts ;
            try {
                contacts = GestionnaireContact.loadContacts("contacts.json");

                //debug pour verfier si les contacts sont bien chargés
                System.out.println(contacts);

                new vueListeContact(contacts);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });
        box.add(showContactsButton);

        // Ajoute le conteneur Box au frame
        frame.add(box);

        // Affiche la fenêtre
        frame.setVisible(true);
    }
}