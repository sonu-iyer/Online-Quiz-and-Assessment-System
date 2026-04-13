package com.soni.pm;

import javax.swing.*;
import java.sql.*;

public class LeaderboardUI extends JFrame {

    public LeaderboardUI() {
        setTitle("Leaderboard");
        setSize(300, 400);

        JTextArea area = new JTextArea();

        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery(
                "SELECT username, MAX(score) as score FROM results GROUP BY username ORDER BY score DESC"
            );

            while (rs.next()) {
                area.append(rs.getString("username") + " - " + rs.getInt("score") + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        add(new JScrollPane(area));
        setVisible(true);
    }
}
