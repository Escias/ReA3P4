package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
    boolean check = true;
    String val;

    public String Register(JTextField addlname, JTextField addfname, JTextField addlog, JPasswordField addpass, JPasswordField repass, JTextField address, JTextField zip, JTextField phone, Connection co) throws SQLException {
        check = true;
        if (check == true){
            if (addlname.getText().isEmpty() || addfname.getText().isEmpty() || addlog.getText().isEmpty() || addpass.getText().isEmpty() || repass.getText().isEmpty() || address.getText().isEmpty() || zip.getText().isEmpty() || phone.getText().isEmpty()){
                val = "Complete all info";
                check = false;
            }
        }
        if (check == true){
            String request = "INSERT INTO personal_user (user_lastname, user_firstname, user_mail, user_password, user_adress, user_ZIP, user_phone, user_type) " +
                    "VALUES ('" + addlname.getText() + "', '" + addfname.getText() + "', '" + addlog.getText() + "', '" + addpass.getText() + "', '" + address.getText() + "', '" + zip.getText() + "', '" + phone.getText() + "', 'normal')";
            Statement stm = co.createStatement();
            stm.executeUpdate(request);
            val = "ok";
        }
        return val;
    }
}
