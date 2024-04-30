package com.forum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ForumScreen3 extends JFrame {
    private JList<String> threadList;

    public ForumScreen3(String userID) {
        setTitle("Rejoindre un fil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        ArrayList<String> threads = getThreadsFromDatabase();



        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String thread : threads) {
            listModel.addElement(thread);
        }
        threadList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(threadList);


           add(scrollPane, BorderLayout.CENTER);


        JMenuBar menuBar = new JMenuBar();
        JButton createThreadButton = new JButton("Accueil");
        JButton joinThreadButton = new JButton("Créer un fil");
        
        createThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour créer un fil (ForumScreen2)
                dispose();
                new ForumScreen(userID);
                
            }
        });
        
        joinThreadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour rejoindre un fil (ForumScreen3)
                dispose();
                new ForumTemplate(userID);
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createThreadButton);
        buttonPanel.add(joinThreadButton);

        add(buttonPanel, BorderLayout.NORTH);
        menuBar.add(createThreadButton);
        menuBar.add(joinThreadButton);

;


        add(menuBar, BorderLayout.NORTH);


        setVisible(true);
        setLocationRelativeTo(null);
    }

     private ArrayList<String> getThreadsFromDatabase() {
        ArrayList<String> threads = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/db_forum";
        String user = "root";
        String password = "password";
        String sqlSelect = "SELECT * FROM threads";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelect)) {
            while (resultSet.next()) {
                String userID = resultSet.getString("userID");
                String titre = resultSet.getString("titre");
                String description = resultSet.getString("description");

                threads.add("UserID: " + userID + ", Titre: " + titre + ", Description: " + description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return threads;
    }
}