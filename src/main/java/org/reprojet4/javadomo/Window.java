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
    JTextField login = new JTextField(10);
    JPasswordField password = new JPasswordField(10);
    JButton blog = new JButton("Sign In");
    JButton breg = new JButton("Register");
    JLabel label = new JLabel("Mail/Password Incorrect");
    Box win1 = Box.createHorizontalBox();
    Box win2 = Box.createHorizontalBox();
    Box win3 = Box.createHorizontalBox();
    Box win4 = Box.createHorizontalBox();
    Box win5 = Box.createHorizontalBox();
    Box win6 = Box.createHorizontalBox();
    Box win7 = Box.createHorizontalBox();
    Box wincol1 = Box.createVerticalBox();
    JCheckBox check = new JCheckBox();
    CreateIni createIni = new CreateIni();
    int id;
    String role;
    String nolog;
    String lastname;
    String firstname;
    String mail;
    String phonenumber;
    String adre;
    String ZIP;
    public String[] infoUser = {"0", "role", "nolog", "lastname", "firstname", "mail", "phone", "address", "ZIP"};

    protected void Window() throws SQLException {
        Connection co = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        window.setTitle("Javadomo");
        window.setSize(700,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        win1.add(new JLabel("login"));
        win1.add(login);
        win2.add(new JLabel("password"));
        win2.add(password);
        win3.add(check);
        win3.add(new JLabel("Remember profile"));
        win4.add(blog);
        win4.add(breg);
        win5.add(new JLabel(" "));
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        panel.add(wincol1);
        window.add(panel, BorderLayout.NORTH);
        createIni.Check(login, password);
        blog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoUser = connectSLQ.ConnectSQL(login, password, co, check);
                    id = Integer.parseInt(infoUser[0]);
                    role = infoUser[1];
                    nolog = infoUser[2];
                    lastname = infoUser[3];
                    firstname = infoUser[4];
                    mail = infoUser[5];
                    phonenumber = infoUser[6];
                    adre = infoUser[7];
                    ZIP = infoUser[8];
                    if(nolog == "fail"){
                        window.getContentPane().remove(label);
                        window.add(label);
                        window.setVisible(true);
                    }else{
                        Menu(id, role, lastname, firstname, mail, phonenumber, adre, ZIP);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        breg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register(co);
            }
        });
        window.setVisible(true);
    }
    JButton brequest = new JButton("Request");
    JButton bupdate = new JButton("Update");
    JButton binsert = new JButton("Insert");
    JButton bdelete = new JButton("Delete");
    JButton bdisconnect = new JButton("Disconnect");
    JButton bvalid = new JButton("Validate");
    JButton breturn = new JButton("Return");
    private String[] req = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Info personnel", "Photo", "Salle", "Capteur", "Thermostats"};
    JComboBox scroll = new JComboBox(req);
    int table;
    String buttext;
    Request request = new Request();


    JTextField addlname = new JTextField(10);
    JTextField addfname = new JTextField(10);
    JTextField addlog = new JTextField(20);
    JPasswordField addpass = new JPasswordField(10);
    JPasswordField repass = new JPasswordField(10);
    JTextField address = new JTextField(30);
    JTextField zip = new JTextField(5);
    JTextField phone = new JTextField(10);
    Register register = new Register();
    String val;

    public void Register(Connection co){
        RemoveAll();
        win1.add(new JLabel("Lastname"));
        win1.add(addlname);
        win1.add(new JLabel("Firstname"));
        win1.add(addfname);
        win2.add(new JLabel("Mail"));
        win2.add(addlog);
        win3.add(new JLabel("Password"));
        win3.add(addpass);
        win3.add(new JLabel("repeat password"));
        win3.add(repass);
        win4.add(new JLabel("Adress"));
        win4.add(address);
        win4.add(new JLabel("ZIP"));
        win4.add(zip);
        win5.add(new JLabel("Phone number"));
        win5.add(phone);
        win6.add(bvalid);
        win6.add(breturn);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        wincol1.add(win6);
        wincol1.add(win7);
        panel.add(wincol1);
        window.add(panel);
        bvalid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    val = register.Register(addlname, addfname, addlog, addpass, repass, address, zip, phone, co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (val == "ok"){
                    try {
                        Disconnect();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                win7.removeAll();
                win7.add(new JLabel(val));
                window.revalidate();
                window.repaint();
            }
        });
        breturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Disconnect();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    JButton bprofil = new JButton("profile");
    JButton bmanage = new JButton("Management");

    private void Menu(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP){
        RemoveAll();
        win1.add(bprofil, BorderLayout.PAGE_START);
        win2.add(bmanage, BorderLayout.CENTER);
        win3.add(bdisconnect, BorderLayout.PAGE_END);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        panel.add(wincol1);
        window.add(panel);
        bprofil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile(role, lastname, firstname, mail, phonenumber, adre, ZIP);
            }
        });
        bmanage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        bdisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfirmDisconnect();
            }
        });
        window.setVisible(true);
    }

    JFrame discoFrame = new JFrame();
    JButton byes = new JButton("Yes");
    JButton bno = new JButton("No");
    JPanel newpanel = new JPanel();
    Box dis1 = Box.createHorizontalBox();
    Box dis2 = Box.createHorizontalBox();
    Box dis3 = Box.createHorizontalBox();
    Box discol1 = Box.createVerticalBox();

    private void ConfirmDisconnect(){
        DiscoRemove();
        discoFrame.setTitle("Javadomo");
        discoFrame.setSize(500,200);
        discoFrame.setLayout(new FlowLayout());
        dis1.add(new JLabel("Are you sure that you"));
        dis2.add(new JLabel("want to disconnect ?"));
        dis3.add(byes);
        dis3.add(bno);
        discol1.add(dis1);
        discol1.add(dis2);
        discol1.add(dis3);
        newpanel.add(discol1);
        discoFrame.add(newpanel);
        byes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discoFrame.dispose();
                try {
                    Disconnect();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        bno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                discoFrame.dispose();
            }
        });
        discoFrame.setVisible(true);
    }

    private void DiscoRemove(){
        dis1.removeAll();
        dis1.revalidate();
        dis1.repaint();
        dis2.removeAll();
        dis2.revalidate();
        dis2.repaint();
        dis3.removeAll();
        dis3.revalidate();
        dis3.repaint();
        discol1.removeAll();
        discol1.revalidate();
        discol1.repaint();
        newpanel.removeAll();
        newpanel.revalidate();
        newpanel.repaint();
    }

    JButton bsupprofile = new JButton("Delete Profile");

    private void Profile(String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP){
        RemoveAll();
        win1.add(new JLabel(lastname + "  "));
        win1.add(new JLabel(firstname));
        win2.add(new JLabel(mail));
        win3.add(new JLabel(phonenumber));
        win4.add(new JLabel(adre + ",    "));
        win4.add(new JLabel(ZIP));
        win5.add(bupdate);
        win5.add(bsupprofile);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        panel.add(wincol1);
        window.add(panel);
        window.setVisible(true);
    }

    private void Request(int id, String role, Connection co){
        RemoveAll();
        win1.add(brequest);
        win1.add(bupdate);
        win1.add(binsert);
        win1.add(bdelete);
        win2.add(bdisconnect);
        wincol1.add(win1);
        wincol1.add(win2);
        panel.add(wincol1);
        window.add(panel);
        brequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttext = brequest.getText();
                SelectTab(id, role , co, buttext);
            }
        });
        bupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttext = bupdate.getText();
                SelectTab(id, role , co, buttext);
            }
        });
        binsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttext = binsert.getText();
                SelectTab(id, role , co, buttext);
            }
        });
        bdelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttext = bdelete.getText();
                SelectTab(id, role , co, buttext);
            }
        });
        bdisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Disconnect();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    private void SelectTab(int id, String role, Connection co, String buttext){
        RemoveAll();
        ScrollTable();
        bvalid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scroll.getSelectedIndex();
                switch (buttext){
                    case "Request":
                        DTable = request.Request(id, role, co, table);
                        DisplayTable(DTable);
                        break;
                    case "Update":
                        break;
                    case "Insert":
                        break;
                    case "Delete":
                        break;
                }
            }
        });
        breturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReturnToRequest(id, role, co);
            }
        });
    }

    JTable DTable = new JTable();

    private void DisplayTable(JTable DTable){
        RemoveTable();
        win3.add(DTable);
        wincol1.add(win3);
        panel.add(wincol1);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    private void ScrollTable(){
        win1.add(scroll);
        win1.add(bvalid);
        win2.add(breturn);
        wincol1.add(win1);
        wincol1.add(win2);
        panel.add(wincol1);
        window.add(panel);
    }

    private void ReturnToRequest(int id, String role, Connection co){
        RemoveAll();
        Request(id, role, co);
    }

    private void Disconnect() throws SQLException {
        RemoveAll();
        Window();
    }

    private void RemoveTable(){
        win3.removeAll();
        win3.revalidate();
        win3.repaint();
        wincol1.remove(win3);
        wincol1.revalidate();
        wincol1.repaint();
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
        win4.removeAll();
        win4.revalidate();
        win4.repaint();
        win5.removeAll();
        win5.revalidate();
        win5.repaint();
        win6.removeAll();
        win6.revalidate();
        win6.repaint();
        win7.removeAll();
        win7.revalidate();
        win7.repaint();
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
