package com.soni.pm;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminUI extends JFrame {

    JTextField q, o1, o2, o3, o4, correct;

    public AdminUI() {
        setTitle("Admin Panel");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2));

        q = new JTextField();
        o1 = new JTextField();
        o2 = new JTextField();
        o3 = new JTextField();
        o4 = new JTextField();
        correct = new JTextField();

        add(new JLabel("Question")); add(q);
        add(new JLabel("Option 1")); add(o1);
        add(new JLabel("Option 2")); add(o2);
        add(new JLabel("Option 3")); add(o3);
        add(new JLabel("Option 4")); add(o4);
        add(new JLabel("Correct (1-4)")); add(correct);

        JButton addBtn = new JButton("Add");
        add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO questions(question, option1, option2, option3, option4, correct_option) VALUES(?,?,?,?,?,?)");

                ps.setString(1, q.getText());
                ps.setString(2, o1.getText());
                ps.setString(3, o2.getText());
                ps.setString(4, o3.getText());
                ps.setString(5, o4.getText());
                ps.setInt(6, Integer.parseInt(correct.getText()));

                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Question Added!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setVisible(true);
    }
}
