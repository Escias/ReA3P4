package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

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

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
