package com.soni.pm;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class QuizUI extends JFrame implements ActionListener {

    JLabel questionLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group;
    JButton nextBtn;
    JProgressBar progressBar;

    List<Question> questions = new ArrayList<>();
    int index = 0, score = 0, timeLeft = 60;
    Timer timer;
    String username;

    public QuizUI(String user) {
        this.username = user;

        setTitle("Quiz");
        setSize(600, 400);
        setLayout(null);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 30, 500, 30);
        add(questionLabel);

        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 80 + i * 30, 500, 30);
            group.add(options[i]);
            add(options[i]);
        }

        nextBtn = new JButton("Next");
        nextBtn.setBounds(250, 250, 100, 30);
        add(nextBtn);

        timerLabel = new JLabel("Time: 60");
        timerLabel.setBounds(500, 10, 100, 30);
        add(timerLabel);

        progressBar = new JProgressBar(0, 60);
        progressBar.setBounds(50, 300, 500, 20);
        add(progressBar);

        nextBtn.addActionListener(this);

        loadQuestions();
        showQuestion();
        startTimer();

        setVisible(true);
    }

    void loadQuestions() {
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM questions");

            while (rs.next()) {
                String[] opt = {
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4")
                };
                questions.add(new Question(
                        rs.getInt("id"),
                        rs.getString("question"),
                        opt,
                        rs.getInt("correct_option")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showQuestion() {
        if (index < questions.size()) {
            Question q = questions.get(index);
            questionLabel.setText("Q" + (index + 1) + ": " + q.question);

            for (int i = 0; i < 4; i++)
                options[i].setText(q.options[i]);

            group.clearSelection();
        } else {
            finishQuiz();
        }
    }

    void startTimer() {
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);
            progressBar.setValue(60 - timeLeft);

            if (timeLeft <= 0) {
                timer.stop();
                finishQuiz();
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        int ans = -1;

        for (int i = 0; i < 4; i++)
            if (options[i].isSelected())
                ans = i + 1;

        if (ans == questions.get(index).correct)
            score++;

        index++;
        showQuestion();
    }

    void finishQuiz() {
        timer.stop();

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO results(username, score) VALUES(?, ?)");
            ps.setString(1, username);
            ps.setInt(2, score);
            ps.executeUpdate();
        } catch (Exception e) {
        }

        JOptionPane.showMessageDialog(this, "Score: " + score);
        new LeaderboardUI();
        dispose();
    }
}

