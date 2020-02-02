package org.reprojet4.javadomo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Window{
    ConnectSLQ connectSLQ = new ConnectSLQ();
    JFrame window = new JFrame();
    JPanel panel = new JPanel();
    JTextField login = new JTextField("jimmyl@projet.com",10);
    JPasswordField password = new JPasswordField("ascrgn91",10);
    JButton blog = new JButton("Sign In");
    Box win1 = Box.createHorizontalBox();
    Box win2 = Box.createHorizontalBox();
    Box win3 = Box.createHorizontalBox();
    Box wincol1 = Box.createVerticalBox();
    String role;
    String nolog;
    int id;
    public String[] infoUser = {"id", "role", "nolog"};

    protected void Window() throws SQLException {
        Connection co = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        window.setTitle("Javadomo");
        window.setSize(700,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win1.add(new JLabel("login"));
        win1.add(login);
        win2.add(new JLabel("password"));
        win2.add(password);
        win3.add(blog);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        panel.add(wincol1);
        window.add(panel, BorderLayout.NORTH);
        blog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoUser = connectSLQ.ConnectSQL(login, password, co);
                    id = Integer.parseInt(infoUser[0]);
                    role = infoUser[1];
                    nolog = infoUser[2];
                    if(nolog == "fail"){
                        window.add(new JLabel("Username/Password incorrect"));
                    }
                    Request(id, role, co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    JButton brequest = new JButton("Request");
    JButton bupdate = new JButton("Update");
    JButton binsert = new JButton("Insert");
    JButton bdelete = new JButton("Delete");
    JButton bvalid = new JButton("Validate");
    JButton breturn = new JButton("Return");
    private String[] req = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Info personnel", "Photo", "Salle", "Capteur", "Thermostats"};
    JComboBox scroll = new JComboBox(req);
    int table;
    String buttext;
    Request request = new Request();
    SelectTable selectTable = new SelectTable();

    private void Request(int id, String role, Connection co){
        RemoveAll();
        win1.add(brequest);
        win1.add(bupdate);
        win1.add(binsert);
        win1.add(bdelete);
        panel.add(win1);
        window.add(panel);
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveAll();
                win1.add(scroll);
                win1.add(bvalid);
                win2.add(breturn);
                wincol1.add(win1);
                wincol1.add(win2);
                panel.add(wincol1);
                window.add(panel);
                bvalid.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttext = brequest.getText();
                        table = scroll.getSelectedIndex();
                        selectTable.SelectTable(table, id, role, co, buttext);
                    }
                });
                breturn.addActionListener(Return());
            }
        });
        bupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveAll();
            }
        });
        binsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveAll();
            }
        });
        bdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveAll();
            }
        });
    }

    private void Return(){

    }

    private void RemoveAll(){
        win1.removeAll();
        win1.revalidate();
        win1.repaint();
        win2.removeAll();
        win2.revalidate();
        win2.repaint();
        win3.removeAll();
        win3.revalidate();
        win3.repaint();
        wincol1.removeAll();
        wincol1.revalidate();
        wincol1.repaint();
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
        window.getContentPane().removeAll();
        window.revalidate();
        window.repaint();
    }
}
