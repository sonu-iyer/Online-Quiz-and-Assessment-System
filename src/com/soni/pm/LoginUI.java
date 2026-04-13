package com.soni.pm;

import javax.swing.*;
import java.awt.*;

public class LoginUI extends JFrame {

    JTextField userField;

    public LoginUI() {
        setTitle("Login");
        setSize(300, 200);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Username:"));
        userField = new JTextField(15);
        add(userField);

        JButton startBtn = new JButton("Start Quiz");
        JButton adminBtn = new JButton("Admin");

        add(startBtn);
        add(adminBtn);

        startBtn.addActionListener(e -> {
            new QuizUI(userField.getText());
            dispose();
        });

        adminBtn.addActionListener(e -> new AdminUI());

        setVisible(true);
    }
}
