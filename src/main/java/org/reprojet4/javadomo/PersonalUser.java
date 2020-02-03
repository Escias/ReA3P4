package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class PersonalUser {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co, String role){
        String request = "SELECT user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP, user_type " +
                "FROM personal_user " +
                "WHERE user_id = "+id+
                " ORDER BY user_lastname ASC;";
        String[] t = {"nom", "prénom", "mail", "téléphone", "adresse", "ZIP", "type"};
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
