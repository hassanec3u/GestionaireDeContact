package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class vueListeContact extends JFrame {

    public vueListeContact(List<Contact> contacts) {
        setTitle("Liste des contacts");
        setSize(500, 1000);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"ID", "Prénom", "Nom", "Téléphone", "Email"};

        // Data for the table
        String[][] data = new String[contacts.size()][5];
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            data[i][0] = contact.getId();
            data[i][1] = contact.getFirstName();
            data[i][2] = contact.getName();
            data[i][3] = contact.getPhone();
            data[i][4] = contact.getEmail();
        }

        // Create table with data
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}