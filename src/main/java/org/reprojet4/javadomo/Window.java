package org.reprojet4.javadomo;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    JPanel pleft = new JPanel();
    JPanel pcenter = new JPanel();
    JPanel pright = new JPanel();

    protected void Window() throws SQLException {
        Connection co = DriverManager.getConnection("jdbc:mysql://localhost:8889/projet4", "root", "root");
        window.setTitle("Javadomo");
        window.setSize(1000,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
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
        panel.add(wincol1, BorderLayout.WEST);
        panel.setLayout(new FlowLayout());
        window.add(panel, BorderLayout.NORTH);
        createIni.Check(login, password);
        blog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    infoUser = connectSLQ.ConnectSQL(login, password, co, check);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
    JButton bupdate = new JButton("Update");
    JButton bdisconnect = new JButton("Disconnect");
    JButton bvalid1 = new JButton("Validate");
    JButton breturn1 = new JButton("Return");
    private String[] req = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Info personnel", "Photo", "Salle", "Capteur", "Thermostats"};
    JComboBox scroll = new JComboBox(req);
    int table;
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
    JPanel pcontent = new JPanel();

    private void RemoveMenu(){
        pleft.removeAll();
        pleft.revalidate();
        pleft.repaint();
        pcontent.remove(pleft);
        pcontent.revalidate();
        pcontent.repaint();
        window.getContentPane().remove(pcontent);
        window.add(pcontent);
        window.revalidate();
        window.repaint();
    }

    private void Menu(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveAll();
        RemoveMenu();
        RemoveCenter();
        RemoveRight();
        pcontent.setSize(window.getWidth(), window.getHeight());
        pcontent.setLayout(new BorderLayout());
        pleft.setLayout(new GridLayout(10, 1));
        pleft.add(bprofil);
        pleft.add(bmanage);
        pleft.add(bdisconnect);
        pleft.setBackground(Color.BLUE);
        pcontent.add(pleft, BorderLayout.LINE_START);
        window.revalidate();
        window.repaint();
        bprofil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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
    ImageIcon img = new ImageIcon();
    ImageIcon image = new ImageIcon();

    private ImageIcon DisplayImageProfile(String imgpath){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imgpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        image = new ImageIcon(dimg);
        return image;
    }

    String imgpath;
    Box center1 = Box.createHorizontalBox();
    Box center2 = Box.createHorizontalBox();
    Box center3 = Box.createHorizontalBox();
    Box center4 = Box.createHorizontalBox();
    Box center5 = Box.createHorizontalBox();
    Box center6 = Box.createHorizontalBox();
    Box center7 = Box.createHorizontalBox();
    Box centercol = Box.createVerticalBox();

    private void RemoveCenter(){
        center1.removeAll();
        center1.revalidate();
        center1.repaint();
        center2.removeAll();
        center2.revalidate();
        center2.repaint();
        center3.removeAll();
        center3.revalidate();
        center3.repaint();
        center4.removeAll();
        center4.revalidate();
        center4.repaint();
        center5.removeAll();
        center5.revalidate();
        center5.repaint();
        center6.removeAll();
        center6.revalidate();
        center6.repaint();
        center7.removeAll();
        center7.revalidate();
        center7.repaint();
        centercol.removeAll();
        centercol.revalidate();
        centercol.repaint();
        pcenter.removeAll();
        pcenter.revalidate();
        pcenter.repaint();
        pcontent.remove(pcenter);
        pcontent.revalidate();
        pcontent.repaint();
    }

    private void Profile(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co) throws SQLException {
        RemoveCenter();
        RemoveRight();
        imgpath = personalUser.TakeImg(id, co);
        img = DisplayImageProfile(imgpath);
        pcenter.setLayout(new FlowLayout());
        pcenter.setLayout(new GridBagLayout());
        center1.add(new JLabel(img));
        center1.add(new JLabel("   "+lastname + "  "));
        center1.add(new JLabel(firstname));
        center2.add(new JLabel(mail));
        center3.add(new JLabel(phonenumber));
        center4.add(new JLabel(adre + ",    "));
        center4.add(new JLabel(ZIP));
        center5.add(bupdate);
        center5.add(buppass);
        center5.add(bsupprofile);
        center5.add(breturn2);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center3);
        centercol.add(center4);
        centercol.add(center5);
        pcenter.add(centercol);
        pcenter.setBackground(Color.WHITE);
        pcontent.add(pcenter, BorderLayout.CENTER);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
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
        RemoveCenter();
        ResetRegister();
        center1.add(new JLabel("Password"));
        center1.add(addpass);
        center2.add(new JLabel("Repeat Password"));
        center2.add(repass);
        center3.add(bvalid2);
        center3.add(breturn3);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center3);
        centercol.add(center5);
        centercol.add(center7);
        pcenter.add(centercol);
        pcontent.add(pcenter, BorderLayout.CENTER);
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
                    try {
                        Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                center5.removeAll();
                center5.add(new JLabel(valupdate));
                window.revalidate();
                window.repaint();
            }
        });
        breturn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        window.setVisible(true);
    }

    JButton breturn4 = new JButton("Return");
    JButton bvalid3 = new JButton("Validate");

    private void UpdateProfile(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveCenter();
        ResetRegister();
        center1.add(new JLabel("Lastname"));
        addlname.setText(lastname);
        center1.add(addlname);
        center1.add(new JLabel("Firstname"));
        addfname.setText(firstname);
        center1.add(addfname);
        center2.add(new JLabel("Mail"));
        addlog.setText(mail);
        center2.add(addlog);
        center4.add(new JLabel("Adress"));
        address.setText(adre);
        center4.add(address);
        center4.add(new JLabel("ZIP"));
        zip.setText(ZIP);
        center4.add(zip);
        center5.add(new JLabel("Phone number"));
        phone.setText(phonenumber);
        center5.add(phone);
        center6.add(bvalid3);
        center6.add(breturn4);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center4);
        centercol.add(center5);
        centercol.add(center6);
        centercol.add(center7);
        pcenter.add(centercol);
        pcontent.add(pcenter, BorderLayout.CENTER);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
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
                    try {
                        Profile(id, role, last, first, email, number, adress, code, co);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                center7.removeAll();
                center7.add(new JLabel(valupdate));
                window.revalidate();
                window.repaint();
            }
        });
        breturn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Profile(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
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

    JButton badditem = new JButton("Add Item");
    JButton blistitem = new JButton("Item management");
    JButton bupitem = new JButton("Update Item");
    JButton breturn5 = new JButton("Return");

    private void RemoveRight(){
        pright.removeAll();
        pright.revalidate();
        pright.repaint();
        pcontent.remove(pright);
    }

    private void Management(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveRight();
        RemoveCenter();
        pright.setLayout(new GridLayout(10,1));
        pright.setBackground(Color.RED);
        pright.add(blistitem);
        pright.add(bupitem);
        pright.add(badditem);
        pright.add(breturn5);
        pcontent.add(pright, BorderLayout.LINE_END);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
        badditem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        blistitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectTab(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        bupitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateItem(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
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

    JButton bre = new JButton("return");
    JButton bre2 = new JButton("return");
    JButton bselectitem = new JButton("Select Table");
    JButton bdeleteitem = new JButton("Delete selected item");
    JButton bupdateitem = new JButton("Update selected item");
    JButton baddnewitem = new JButton("Validate");
    int orderby;
    int selection1;
    int selection2;
    int selection3;
    int selection4;
    int row;
    int valuetab;
    String valtab;
    Integer obj1;
    JTable tabupdate = new JTable();
    JButton breturn6 = new JButton("return");
    private String[] requp = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Info personnel", "Photo", "Salle", "Capteur", "Thermostats"};
    JComboBox scrollup = new JComboBox(requp);

    private void UpdateItem(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveCenter();
        center1.add(scrollup);
        tabupdate = ampConnect.UpdateAdd(co, id, role);
        DisplayTable(tabupdate);
        center1.add(upScroll);
        center1.add(therm1);
        center1.add(therm2);
        center1.add(upPlusScroll);
        center1.add(bupdateitem);
        center3.add(breturn6);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center3);
        centercol.add(center4);
        pcenter.add(centercol);
        pcontent.add(pcenter, BorderLayout.CENTER);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
        scrollup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scrollup.getSelectedIndex();
                obj1 = table;
                table = scrollup.getSelectedIndex();
                center1.removeAll();
                center2.removeAll();
                center1.add(scrollup);
                try {
                    tabupdate = UpdateOption(table, role, id , co);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (obj1.equals(0) || obj1.equals(1) || obj1.equals(3) || obj1.equals(4) || obj1.equals(5) || obj1.equals(7) || obj1.equals(8) || obj1.equals(9)){
                    DisplayTable(tabupdate);
                }
                center1.add(bupdateitem);
                center1.revalidate();
                center1.repaint();
                center2.revalidate();
                center2.repaint();
                window.revalidate();
                window.repaint();
            }
        });
        bupdateitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scrollup.getSelectedIndex();
                selection1 = upScroll.getSelectedIndex() + 1;
                selection2 = upPlusScroll.getSelectedIndex() + 1;
                selection3 = therm1.getSelectedIndex() + 1;
                selection4 = therm2.getSelectedIndex() + 1;
                row = tablist.getSelectedRow();
                valtab = tablist.getValueAt(row, 0).toString();
                valuetab = Integer.parseInt(valtab);
                try {
                    request.Update(id, role, co, table, selection1, selection2, selection3, selection4, tabupdate, valuetab);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        breturn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    JComboBox upScroll = new JComboBox();
    JComboBox upPlusScroll = new JComboBox();

    private JTable UpdateOption(int table, String role, int id, Connection co) throws SQLException {
        element = null;
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
                upScroll = new JComboBox(element);
                tabupdate = ampConnect.UpdateAdd(co, id, role);
                plus = new String[3];
                plus[0] = "on";
                plus[1] = "off";
                plus[2] = "scheduled";
                upPlusScroll = new JComboBox(plus);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center1.add(upScroll);
                center1.add(upPlusScroll);
                break;
            case 1: // CamInstall
                ls = camInstall.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                upScroll = new JComboBox(element);
                tabupdate = camInstall.UpdateAdd(co, id, role);
                plus = new String[2];
                plus[0] = "on";
                plus[1] = "off";
                upPlusScroll = new JComboBox(plus);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center1.add(upScroll);
                center1.add(upPlusScroll);
                break;
            case 2: // Datamp
                center1.remove(tableAdd);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center2.add(new JLabel("Unable to change data (Security)"));
                break;
            case 3: // Datatemp
                center2.removeAll();
                center1.remove(tableAdd);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center2.add(new JLabel("Unable to change data (Security)"));
                center2.revalidate();
                center2.repaint();
                break;
            case 4: // Food
                ls = food.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                upScroll = new JComboBox(element);
                tabupdate = food.UpdateAdd(co, id, role);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center1.add(upScroll);
                break;
            case 5: //PersonalUser
                if (role.equals("admin")){
                    plus = new String[2];
                    plus[0] = "admin";
                    plus[1] = "normal";
                    upPlusScroll = new JComboBox(plus);
                    tabupdate = personalUser.UpdateAdd(co, role);
                    center1.remove(upPlusScroll);
                    center1.remove(upScroll);
                    center1.remove(upPlusScroll);
                    center1.add(upPlusScroll);
                }else {
                    center1.remove(upScroll);
                    center1.remove(upPlusScroll);
                    center2.add(new JLabel("Go to your profile to update it"));
                }
                break;
            case 6: // Photo
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center2.add(new JLabel("Unable to modify photo"));
                break;
            case 7: // Room
                tabupdate = room.UpdateAdd(co, id, role);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                break;
            case 8: // Sensor
                ls = sensor.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                upScroll = new JComboBox(element);
                tabupdate = sensor.UpdateAdd(co, id, role);
                plus = new String[2];
                plus[0] = "on";
                plus[1] = "off";
                upPlusScroll = new JComboBox(plus);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center1.add(upScroll);
                center1.add(upPlusScroll);
                break;
            case 9: // ThermoIntel
                ls = thermoIntel.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                upScroll = new JComboBox(element);
                thermo1 = thermoIntel.InsertAdd1(id, co, role);
                ther1 = thermo1.toArray(new String[thermo1.size()]);
                therm1 = new JComboBox(ther1);
                thermo2 = thermoIntel.InsertAdd1(id, co, role);
                ther2 = thermo2.toArray(new String[thermo2.size()]);
                therm2 = new JComboBox(ther2);
                tabupdate = thermoIntel.UpdateAdd(co, id, role);
                plus = new String[3];
                plus[0] = "inactif";
                plus[1] = "chaud";
                plus[2] = "froid";
                upPlusScroll = new JComboBox(plus);
                center1.remove(upScroll);
                center1.remove(upPlusScroll);
                center1.add(upScroll);
                center1.add(therm1);
                center1.add(therm2);
                center1.add(upPlusScroll);
                break;
            default:
                break;
        }
        return tabupdate;
    }

    private String[] reqad = {"Ampoule Connectée", "Caméra installée", "Donnée Ampoule", "Donnée Thermos", "Nourriture", "Info personnel", "Photo", "Salle", "Capteur", "Thermostats"};
    JComboBox scrollad = new JComboBox(reqad);

    private void InsertItem (int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveCenter();
        center1.add(scrollad);
        DefaultTableModel aModel = (DefaultTableModel) tableAdd.getModel();
        aModel.setColumnIdentifiers(column);
        tableAdd.setModel(aModel);
        paneAdd = new JScrollPane(tableAdd);
        paneAdd.setSize(5, 10);
        center2.add(paneAdd);
        center1.add(AddScroll);
        center1.add(therm1);
        center1.add(therm2);
        center1.add(AddPlusScroll);
        center1.add(baddnewitem);
        center3.add(bre2);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center3);
        centercol.add(center4);
        pcenter.add(centercol);
        pcontent.add(pcenter, BorderLayout.CENTER);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
        scrollad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scrollad.getSelectedIndex();
                obj1 = table;
                table = scrollad.getSelectedIndex();
                center1.removeAll();
                center2.removeAll();
                center1.add(scrollad);
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
                    center2.add(paneAdd);
                }
                center1.add(baddnewitem);
                center1.revalidate();
                center1.repaint();
                center2.revalidate();
                center2.repaint();
                window.revalidate();
                window.repaint();
            }
        });
        baddnewitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scrollad.getSelectedIndex();
                orderby = ListScroll.getSelectedIndex();
                selection1 = AddScroll.getSelectedIndex() + 1;
                selection2 = AddPlusScroll.getSelectedIndex() + 1;
                selection3 = therm1.getSelectedIndex() + 1;
                selection4 = therm2.getSelectedIndex() + 1;
                row = tablist.getSelectedRow();
                try {
                    request.Insert(id, role, co, table, tableAdd, selection1, selection2, selection3, selection4);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
        bre2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Management(id, role, lastname, firstname, mail, phonenumber, adre, ZIP, co);
            }
        });
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    JTable tablist = new JTable();
    JButton bphoto = new JButton("Show Photo");
    String pathimg;
    Integer obj2;

    private void SelectTab(int id, String role, String lastname, String firstname, String mail, String phonenumber, String adre, String ZIP, Connection co){
        RemoveCenter();
        center1.add(scroll);
        center1.add(ListScroll);
        center1.add(bselectitem);
        center1.add(bdeleteitem);
        tablist = request.Request(co, table, orderby);
        DisplayTable(tablist);
        center2.add(DTable);
        table = scroll.getSelectedIndex();
        obj2 = table;
        if (obj2.equals(2)){
            center3.add(bphoto);
        }
        center3.add(bre);
        centercol.add(center1);
        centercol.add(center2);
        centercol.add(center3);
        centercol.add(center4);
        pcenter.add(centercol);
        pcontent.add(pcenter, BorderLayout.CENTER);
        pcontent.revalidate();
        pcontent.repaint();
        scroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scroll.getSelectedIndex();
                orderby = ListScroll.getSelectedIndex();
                tablist = request.Request(co, table, orderby);
                center1.removeAll();
                center2.removeAll();
                center1.add(scroll);
                ListOption(table);
                center1.add(bselectitem);
                center1.add(bdeleteitem);
                DisplayTable(tablist);
                center3.add(bre);
                window.revalidate();
                window.repaint();
            }
        });
        bselectitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                center2.removeAll();
                center3.removeAll();
                table = scroll.getSelectedIndex();
                obj2 = table;
                orderby = ListScroll.getSelectedIndex();
                row = tablist.getSelectedRow();
                tablist = request.Request(co, table, orderby);
                DisplayTable(tablist);
                if (obj2.equals(6)){
                    center3.add(bphoto);
                }
                center3.add(bre);
            }
        });
        bdeleteitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row = tablist.getSelectedRow();
                orderby = ListScroll.getSelectedIndex();
                center4.removeAll();
                if (role.equals("admin")){
                    if (row > 0){
                        valtab = tablist.getValueAt(row, 0).toString();
                        valuetab = Integer.parseInt(valtab);
                        try {
                            request.Delete(table, valuetab, co);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        center2.removeAll();
                        DelOption(table, co, orderby);
                        center2.revalidate();
                        center2.repaint();
                        window.revalidate();
                        window.repaint();
                    }else{
                        center4.add(new JLabel("Select an element to delete"));
                        center4.revalidate();
                        center4.repaint();
                        window.revalidate();
                        window.repaint();
                    }
                }else{
                    center4.add(new JLabel("Ask an admin to delete an element"));
                }
            }
        });
        bphoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table = scroll.getSelectedIndex();
                obj2 = table;
                if (obj2.equals(6)){
                    row = tablist.getSelectedRow();
                    valtab = tablist.getValueAt(row, 0).toString();
                    valuetab = Integer.parseInt(valtab);
                    try {
                        pathimg = photo.TakePath(valuetab, co);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    File file = new File(pathimg);
                    if(!Desktop.isDesktopSupported()){
                        System.out.println("Desktop is not supported");
                        return;
                    }
                    Desktop desktop = Desktop.getDesktop();
                    if(file.exists()) {
                        try {
                            desktop.open(file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
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

    PersonalUser personalUser = new PersonalUser();
    Photo photo = new Photo();
    Room room = new Room();

    private void DelOption(int table, Connection co, int orderby){
        switch (table){
            case 0:
                tablist = ampConnect.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 1:
                tablist = camInstall.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 2:
                tablist = datamp.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 3:
                tablist = datatemp.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 4:
                tablist = food.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 5:
                tablist = personalUser.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 6:
                tablist = photo.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 7:
                tablist = room.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 8:
                tablist = sensor.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
            case 9:
                tablist = thermoIntel.Request(co, orderby);
                DisplayTable(tablist);
                center2.removeAll();
                center2.add(DTable);
                break;
        }
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

    private void ListOption(int table){
        switch (table){
            case 0:
                ListScroll = new JComboBox(orderAmpConnect);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 1:
                ListScroll = new JComboBox(orderCamInstall);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 2:
                ListScroll = new JComboBox(orderDatAmp);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 3:
                ListScroll = new JComboBox(orderDataTemp);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 4:
                ListScroll = new JComboBox(orderFood);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 5:
                ListScroll = new JComboBox(orderPersonalUser);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 6:
                ListScroll = new JComboBox(orderPhoto);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                center3.add(bphoto);
                break;
            case 7:
                ListScroll = new JComboBox(orderRoom);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 8:
                ListScroll = new JComboBox(orderSensor);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
                break;
            case 9:
                ListScroll = new JComboBox(orderThermoIntel);
                center1.remove(ListScroll);
                center3.remove(bphoto);
                center1.add(ListScroll);
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
    Datatemp datatemp = new Datatemp();
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
    JComboBox AddScroll = new JComboBox();
    JComboBox AddPlusScroll = new JComboBox();

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
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                center1.add(AddPlusScroll);
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
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                center1.add(AddPlusScroll);
                break;
            case 2: // Datamp
                ls = datamp.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                plus = new String[2];
                plus[0] = "allumer";
                plus[1] = "éteindre";
                AddPlusScroll = new JComboBox(plus);
                center1.remove(tableAdd);
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                center1.add(AddPlusScroll);
                break;
            case 3: // Datatemp
                ls = datatemp.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[1];
                column[0] = "temperature";
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                break;
            case 4: // Food
                ls = food.InsertAdd(id, co, role);
                element = ls.toArray(new String[ls.size()]);
                AddScroll = new JComboBox(element);
                column = new String[3];
                column[0] = "name";
                column[1] = "peremption";
                column[2] = "quantity";
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
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
                    center1.remove(AddPlusScroll);
                    center1.remove(AddScroll);
                    center1.remove(AddPlusScroll);
                    center1.add(AddPlusScroll);
                }else {
                    center1.remove(AddScroll);
                    center1.remove(AddPlusScroll);
                    center1.add(new JLabel("You can't use this option"));
                }
                break;
            case 6: // Photo
                center1.remove(AddScroll);
                center1.add(new JLabel("Go to the list to view photo"));
                break;
            case 7: // Room
                column = new String[2];
                column[0] = "Name";
                column[1] = "Description";
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
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
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                center1.add(AddPlusScroll);
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
                center1.remove(AddScroll);
                center1.remove(AddPlusScroll);
                center1.add(AddScroll);
                center1.add(therm1);
                center1.add(therm2);
                center1.add(AddPlusScroll);
                break;
            default:
                break;
        }
        center1.revalidate();
        center1.repaint();
        return column;
    }

    JScrollPane DTable = new JScrollPane();

    private void DisplayTable(JTable tablist){
        RemoveTable();
        DTable = new JScrollPane(tablist);
        center2.add(DTable);
        centercol.add(center2);
        pcontent.revalidate();
        pcontent.repaint();
        window.revalidate();
        window.repaint();
        window.setVisible(true);
    }

    private void Disconnect() throws SQLException {
        RemoveAll();
        Window();
    }

    private void RemoveTable(){
        center2.removeAll();
        center2.revalidate();
        center2.repaint();
        centercol.remove(center2);
        centercol.revalidate();
        centercol.repaint();
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