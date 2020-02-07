package org.reprojet4.javadomo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Window{
    ConnectSLQ connectSLQ = new ConnectSLQ();
    JFrame window = new JFrame();
    JPanel panel = new JPanel();
    JTextField login = new JTextField(10);
    JPasswordField password = new JPasswordField(10);
    JButton blog = new JButton("Sign In");
    JButton breg = new JButton("Register");
    JButton bexit = new JButton("Exit");
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
    public int id;
    public String role;
    public String nolog;
    public String lastname;
    public String firstname;
    public String mail;
    public String phonenumber;
    public String adre;
    public String ZIP;
    public String[] infoUser = {"0", "role", "nolog", "lastname", "firstname", "mail", "phone", "address", "ZIP"};

    protected void Window() throws SQLException {
        Connection co = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        window.setTitle("Javadomo");
        window.setSize(1000,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new FlowLayout());
        win1.add(new JLabel("login"));
        win1.add(login);
        win2.add(new JLabel("password"));
        win2.add(password);
        win3.add(check);
        win3.add(new JLabel("Rememeber Profile"));
        win4.add(blog);
        win4.add(breg);
        win5.add(bexit);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        panel.add(wincol1);
        panel.setLayout(new FlowLayout());
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
                        Menu(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
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
        bexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.dispose();
            }
        });
        window.setVisible(true);
    }
    JButton brequest = new JButton("Request");
    JButton bupdate = new JButton("Update");
    JButton binsert = new JButton("Insert");
    JButton bdelete = new JButton("Delete");
    JButton bdisconnect = new JButton("Disconnect");
    JButton bvalid1 = new JButton("Validate");
    JButton breturn1 = new JButton("Return");
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

    private void Register(Connection co){
        RemoveAll();
        ResetRegister();
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
        win6.add(bvalid1);
        win6.add(breturn1);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        wincol1.add(win6);
        wincol1.add(win7);
        panel.add(wincol1);
        window.add(panel);
        bvalid1.addActionListener(new ActionListener() {
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
        breturn1.addActionListener(new ActionListener() {
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

    private void Menu(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
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
                Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        bmanage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
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
    JButton buppass = new JButton("Change password");
    JButton breturn2 = new JButton("Return");

    private void Profile(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveAll();
        win1.add(new JLabel(lastname + "  "));
        win1.add(new JLabel(firstname));
        win2.add(new JLabel(mail));
        win3.add(new JLabel(phonenumber));
        win4.add(new JLabel(adre + ",    "));
        win4.add(new JLabel(ZIP));
        win5.add(bupdate);
        win5.add(buppass);
        win5.add(bsupprofile);
        win5.add(breturn2);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        panel.add(wincol1);
        window.add(panel);
        bupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateProfile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        buppass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdatePassword(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        bsupprofile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupProfile(id, co);
            }
        });
        breturn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.setVisible(true);
    }

    private void ResetRegister(){
        addlname.setText(null);
        addfname.setText(null);
        addlog.setText(null);
        addpass.setText(null);
        repass.setText(null);
        address.setText(null);
        zip.setText(null);
        phone.setText(null);
    }

    UpProfile upProfile = new UpProfile();
    JButton breturn3 = new JButton("Return");
    JButton bvalid2 = new JButton("Validate");
    String[] upcheck = {"lastname", "firstname", "mail", "phonenumber", "adre", "zip", "val"};
    String valupdate;
    public String last;
    public String first;
    public String email;
    public String number;
    public String adress;
    public String code;

    private void UpdatePassword(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveAll();
        ResetRegister();
        win1.add(new JLabel("Password"));
        win1.add(addpass);
        win2.add(new JLabel("Repeat Password"));
        win2.add(repass);
        win3.add(bvalid2);
        win3.add(breturn3);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win7);
        panel.add(wincol1);
        window.add(panel);
        bvalid2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uppass = "yes";
                try {
                    upcheck = upProfile.UpProfile(id, addlname, addfname, addlog, address, zip, phone, addpass, repass, co, uppass);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                valupdate = upcheck[6];
                if (valupdate == "ok"){
                    Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
                }
                win7.removeAll();
                win7.add(new JLabel(valupdate));
                window.revalidate();
                window.repaint();
            }
        });
        breturn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.setVisible(true);
    }

    JButton breturn4 = new JButton("Return");
    JButton bvalid3 = new JButton("Validate");

    private void UpdateProfile(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveAll();
        ResetRegister();
        win1.add(new JLabel("Lastname"));
        addlname.setText(lastname);
        win1.add(addlname);
        win1.add(new JLabel("Firstname"));
        addfname.setText(firstname);
        win1.add(addfname);
        win2.add(new JLabel("Mail"));
        addlog.setText(mail);
        win2.add(addlog);
        win4.add(new JLabel("Adress"));
        address.setText(adre);
        win4.add(address);
        win4.add(new JLabel("ZIP"));
        zip.setText(ZIP);
        win4.add(zip);
        win5.add(new JLabel("Phone number"));
        phone.setText(phonenumber);
        win5.add(phone);
        win6.add(bvalid3);
        win6.add(breturn4);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win4);
        wincol1.add(win5);
        wincol1.add(win6);
        wincol1.add(win7);
        panel.add(wincol1);
        window.add(panel);
        bvalid3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String uppass = "";
                    upcheck = upProfile.UpProfile(id, addlname, addfname, addlog, address, zip, phone, addpass, repass, co, uppass);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                last = upcheck[0];
                first = upcheck[1];
                email = upcheck[2];
                number = upcheck[3];
                adress = upcheck[4];
                code = upcheck[5];
                valupdate = upcheck[6];
                if (valupdate == "ok"){
                        Profile(id, role, last, first, email, number, adress, code, co);
                }
                win7.removeAll();
                win7.add(new JLabel(valupdate));
                window.revalidate();
                window.repaint();
            }
        });
        breturn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.setVisible(true);
    }

    private void SupProfile(int id, Connection co){
        DiscoRemove();
        discoFrame.setTitle("Javadomo");
        discoFrame.setSize(500,200);
        discoFrame.setLayout(new FlowLayout());
        dis1.add(new JLabel("Are you sure that you"));
        dis2.add(new JLabel("want to delete your profile ?"));
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
                    upProfile.DelProfile(id, co);
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

    JButton bdelitem = new JButton("Delete an Item");
    JButton badditem = new JButton("Add Item");
    JButton blistitem = new JButton("List Items");
    JButton bupitem = new JButton("Update Items");
    JButton breturn5 = new JButton("Return");

    private void Management(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveAll();
        win1.add(bdelitem);
        win2.add(badditem);
        win3.add(blistitem);
        win4.add(bupitem);
        win5.add(breturn5);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        wincol1.add(win4);
        wincol1.add(win5);
        panel.add(wincol1);
        window.add(panel);
        bdelitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DelItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        badditem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        blistitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        bupitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        breturn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Menu(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.setVisible(true);
    }

    private void DelItem(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        String buttext = "Delete";
        SelectTab(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co, buttext);
    }

    private void AddItem(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        String buttext = "Insert";
        SelectTab(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co, buttext);
    }

    private void ListItem(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        String buttext = "Request";
        SelectTab(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co, buttext);
    }

    private void UpItem(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        String buttext = "Update";
        SelectTab(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co, buttext);
    }

    JButton bre = new JButton("return");
    JButton bvalid4 = new JButton("Validate");
    int orderby;
    int selection1;
    int selection2;
    int selection3;
    int selection4;
    Integer obj1;

    private void SelectTab(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co, String buttext){
        RemoveAll();
        win1.add(scroll);
        if (buttext == "Request"){
            win1.add(ListScroll);
        }else if (buttext == "Insert"){
            DefaultTableModel aModel = (DefaultTableModel) tableAdd.getModel();
            aModel.setColumnIdentifiers(column);
            tableAdd.setModel(aModel);
            paneAdd = new JScrollPane(tableAdd);
            paneAdd.setSize(5, 10);
            win2.add(paneAdd);
            win1.add(AddScroll);
            win1.add(therm1);
            win1.add(therm2);
            win1.add(AddPlusScroll);
        }
        win1.add(bvalid4);
        win3.add(bre);
        wincol1.add(win1);
        wincol1.add(win2);
        wincol1.add(win3);
        panel.add(wincol1);
        window.add(panel);
        scroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scroll.getSelectedIndex();
                obj1 = table;
                switch (buttext){
                    case "Request":
                        table = scroll.getSelectedIndex();
                        win1.removeAll();
                        win2.removeAll();
                        win1.add(scroll);
                        ListOption(table);
                        win1.add(bvalid4);
                        win2.add(paneAdd);
                        window.revalidate();
                        window.repaint();
                        break;
                    case "Update":
                        break;
                    case "Insert":
                        table = scroll.getSelectedIndex();
                        win1.removeAll();
                        win2.removeAll();
                        win1.add(scroll);
                        try {
                            column = AddOption(id, co, role, table);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        DefaultTableModel aModel = (DefaultTableModel) tableAdd.getModel();
                        aModel.setColumnIdentifiers(column);
                        tableAdd.setModel(aModel);
                        paneAdd = new JScrollPane(tableAdd);
                        paneAdd.setSize(5, 10);
                        if (obj1.equals(0) || obj1.equals(1) || obj1.equals(3) || obj1.equals(4) || obj1.equals(5) || obj1.equals(7) || obj1.equals(8) || obj1.equals(9)){
                            win2.add(paneAdd);
                        }
                        win1.add(bvalid4);
                        win1.revalidate();
                        win1.repaint();
                        win2.revalidate();
                        win2.repaint();
                        window.revalidate();
                        window.repaint();
                        break;
                    case "Delete":
                        break;
                }
            }
        });
        bvalid4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scroll.getSelectedIndex();
                orderby = ListScroll.getSelectedIndex();
                selection1 = AddScroll.getSelectedIndex() + 1;
                selection2 = AddPlusScroll.getSelectedIndex() + 1;
                selection3 = therm1.getSelectedIndex() + 1;
                selection4 = therm2.getSelectedIndex() + 1;
                switch (buttext){
                    case "Request":
                        DTable = request.Request(id, role, co, table, orderby);
                        DisplayTable(DTable);
                        break;
                    case "Update":
                        break;
                    case "Insert":
                        try {
                            request.Insert(id, role, co, table, tableAdd, selection1, selection2, selection3, selection4);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        break;
                    case "Delete":
                        break;
                }
            }
        });
        bre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    String[] orderAmpConnect = {"order add", "name", "room", "status", "color", "time on", "time off"};
    String[] orderCamInstall = {"order add", "name", "room", "status", "activation distance", "begin", "end"};
    String[] orderDatAmp = {"order add", "bulb", "action", "date and hour"};
    String[] orderDataTemp = {"order add", "sensor", "temperature", "date of capture"};
    String[] orderFood = {"order add", "room", "name", "expiration", "quantity"};
    String[] orderPersonalUser = {"order add", "lastname", "firstname"};
    String[] orderPhoto = {"order add", "camera", "date of capture"};
    String[] orderRoom = {"order add", "name", "user"};
    String[] orderSensor = {"order add", "name", "room", "status", "interval"};
    String[] orderThermoIntel = {"order add", "room", "name", "thermo 1", "thermo 2", "temperature target", "status"};
    JComboBox ListScroll = new JComboBox(orderAmpConnect);
    JComboBox AddScroll = new JComboBox();
    JComboBox AddPlusScroll = new JComboBox();

    private void ListOption(int table){
        switch (table){
            case 0:
                ListScroll = new JComboBox(orderAmpConnect);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 1:
                ListScroll = new JComboBox(orderCamInstall);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 2:
                ListScroll = new JComboBox(orderDatAmp);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 3:
                ListScroll = new JComboBox(orderDataTemp);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 4:
                ListScroll = new JComboBox(orderFood);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 5:
                ListScroll = new JComboBox(orderPersonalUser);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 6:
                ListScroll = new JComboBox(orderPhoto);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 7:
                ListScroll = new JComboBox(orderRoom);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 8:
                ListScroll = new JComboBox(orderSensor);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            case 9:
                ListScroll = new JComboBox(orderThermoIntel);
                win1.remove(ListScroll);
                win1.add(ListScroll);
                break;
            default:
                break;
        }
    }

    JTable tableAdd = new JTable(1,3);
    List<String> ls = new ArrayList<>();
    List<String> thermo1 = new ArrayList<>();
    List<String> thermo2 = new ArrayList<>();
    JScrollPane paneAdd = new JScrollPane(tableAdd);
    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    Datamp datamp = new Datamp();
    Datatemp datatemp= new Datatemp();
    Food food = new Food();
    Sensor sensor = new Sensor();
    ThermoIntel thermoIntel = new ThermoIntel();
    String[] element;
    String[] column = {"name", "color"};
    String[] plus;
    String[] ther1;
    String[] ther2;
    JComboBox therm1 = new JComboBox();
    JComboBox therm2 = new JComboBox();

    private String[] AddOption(int id, Connection co, String role, int table) throws SQLException {
        element = null;
        column = null;
        plus = null;
        ther1 = null;
        ther2 = null;
        ls.clear();
        thermo1.clear();
        thermo2.clear();
        switch (table){
            case 0: // AmpConnect
                ls = ampConnect.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[2];
                column[0] = "name";
                column[1] = "color";
                plus = new String[3];
                plus[0] = "on";
                plus[1] = "off";
                plus[2] = "scheduled";
                AddPlusScroll = new JComboBox(plus);
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                win1.add(AddPlusScroll);
                break;
            case 1: // CamInstall
                ls = camInstall.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[2];
                column[0] = "name";
                column[1] = "distance";
                plus = new String[2];
                plus[0] = "on";
                plus[1] = "off";
                AddPlusScroll = new JComboBox(plus);
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                win1.add(AddPlusScroll);
                break;
            case 2: // Datamp
                ls = datamp.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                plus = new String[2];
                plus[0] = "allumer";
                plus[1] = "éteindre";
                AddPlusScroll = new JComboBox(plus);
                win1.remove(tableAdd);
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                win1.add(AddPlusScroll);
                break;
            case 3: // Datatemp
                ls = datatemp.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[1];
                column[0] = "temperature";
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                break;
            case 4: // Food
                ls = food.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[3];
                column[0] = "name";
                column[1] = "peremption";
                column[2] = "quantity";
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                break;
            case 5: //PersonalUser
                if (role.equals("admin")){
                    column = new String[7];
                    column[0] = "Last name";
                    column[1] = "First name";
                    column[2] = "Mail";
                    column[3] = "Phone";
                    column[4] = "Address";
                    column[5] = "ZIP";
                    column[6] = "password";
                    plus = new String[2];
                    plus[0] = "admin";
                    plus[1] = "normal";
                    AddPlusScroll = new JComboBox(plus);
                    win1.remove(AddPlusScroll);
                    win1.remove(AddScroll);
                    win1.remove(AddPlusScroll);
                    win1.add(AddPlusScroll);
                }else {
                    win1.remove(AddScroll);
                    win1.remove(AddPlusScroll);
                    win1.add(new JLabel("You can't use this option"));
                }
                break;
            case 6: // Photo
                win1.remove(AddScroll);
                win1.add(new JLabel("Go to the list to view photo"));
                break;
            case 7: // Room
                column = new String[2];
                column[0] = "Name";
                column[1] = "Description";
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                break;
            case 8: // Sensor
                ls = sensor.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[4];
                column[0] = "name";
                column[1] = "interval";
                column[2] = "temp min";
                column[3] = "temp max";
                plus = new String[2];
                plus[0] = "on";
                plus[1] = "off";
                AddPlusScroll = new JComboBox(plus);
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                win1.add(AddPlusScroll);
                break;
            case 9: // ThermoIntel
                ls = thermoIntel.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                thermo1 = thermoIntel.InsertAdd1(id, co, role);
                ther1 = thermo1.toArray(new String[thermo1.size()]);
                therm1 = new JComboBox(ther1);
                thermo2 = thermoIntel.InsertAdd1(id, co, role);
                ther2 = thermo2.toArray(new String[thermo2.size()]);
                therm2 = new JComboBox(ther2);
                column = new String[2];
                column[0] = "name";
                column[1] = "temp target";
                plus = new String[3];
                plus[0] = "inactif";
                plus[1] = "chaud";
                plus[2] = "froid";
                AddPlusScroll = new JComboBox(plus);
                win1.remove(AddScroll);
                win1.remove(AddPlusScroll);
                win1.add(AddScroll);
                win1.add(therm1);
                win1.add(therm2);
                win1.add(AddPlusScroll);
                break;
            default:
                break;
        }
        return column;
    }

    JScrollPane DTable = new JScrollPane();

    private void DisplayTable(JScrollPane DTable){
        RemoveTable();
        win3.add(DTable);
        wincol1.add(win3);
        panel.add(wincol1);
        window.revalidate();
        window.repaint();
        window.setVisible(true);
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
