package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PersonalUser {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
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
        table = tableAdd.Table(co, t, request);
        return table;
    }

    public void Update(int id){

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
                "VALUES ('" + value1 + "', '" + value2 + "', '" + value3 + "', '" + value4 + "', '" + value5 + "', '" + value6 + "', '" + type + "', '" + value7 + "')";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    public void Delete(int id){

    }
}
