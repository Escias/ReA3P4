package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonalUser {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "user_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "user_lastname";
                }
                break;
            case 2:
                if (check == true){
                    order = "user_firstname";
                }
        }
        String request = "SELECT user_id, user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type " +
                "FROM personal_user " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "nom", "prénom", "mail", "téléphone", "adresse", "ZIP", "type"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    public void Update(int id, Connection co, String value1, String value2, String value3, String value4, String value5, String value6, String value7, int selection1) throws SQLException {
        Integer obj1 = selection1;
        if (obj1.equals(1)){
            type = "admin";
        }else if (obj1.equals(2)){
            type = "normal";
        }
        String request = "Update personal_user " +
                "SET user_lastname = '"+value1+"', user_firstname = '"+value2+"', user_mail = '"+value3+"', user_phone = '"+value4+"', user_adress = '"+value5+"', user_ZIP = '"+value6+"', user_type = '"+type+"', user_password = '"+value7+"'" +
                "WHERE cam_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    public JTable UpdateAdd(Connection co, String role){
        if (role.equals("admin")){
            String request = "SELECT user_id, user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_password " +
                    "FROM personal_user " +
                    "WHERE user_type != 'admin';";
            String[] t = {"id", "nom", "prénom", "mail", "téléphone", "adresse", "ZIP", "Password"};
            table = tableAdd.Tab(co, t, request);
        }
        return table;
    }

    String type;

    public void Insert(String value1, String value2, String value3, String value4, String value5, String value6, String value7, int selection1, Connection co) throws SQLException {
        Integer obj1 = selection1;
        if (obj1.equals(1)){
            type = "admin";
        }else if (obj1.equals(2)){
            type = "normal";
        }
        String request = "INSERT INTO personal_user (user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type, user_password) " +
                "VALUES ('" + value1 + "', '" + value2 + "', '" + value3 + "', '" + value4 + "', '" + value5 + "', '" + value6 + "', '" + type + "', '" + value7 + "');";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM personal_user WHERE user_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    String imgpath;

    public String TakeImg(int id, Connection co) throws SQLException {
        String request = "SELECT icon FROM personal_user WHERE user_id = "+id+";";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        if (rslt.next()){
            imgpath = rslt.getString(1);
        }
        return imgpath;
    }
}
