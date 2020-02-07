package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Register {
    boolean check = true;
    String val;

    public String Register(JTextField addlname, JTextField addfname, JTextField addlog, JPasswordField addpass, JPasswordField repass, JTextField address, JTextField zip, JTextField phone, Connection co) throws SQLException {
        check = true;
        System.out.println(addlname.getText().length());
        System.out.println(addfname.getText().length());
        System.out.println(addlog.getText().length());
        System.out.println(addpass.getText().length());
        System.out.println(repass.getText().length());
        System.out.println(address.getText().length());
        System.out.println(zip.getText().length());
        System.out.println(phone.getText().length());
        if (check == true){
            if (addlname.getText().length() == 0 || addfname.getText().length() == 0 || addlog.getText().length() == 0 || addpass.getText().length() == 0 || repass.getText().length() == 0 || address.getText().length() == 0 || zip.getText().length() == 0 || phone.getText().length() == 0){
                val = "Complete all info";
                check = false;
            }
        }
        if (check == true){
            if (Arrays.equals(addpass.getPassword(), repass.getPassword())){
                String request = "INSERT INTO personal_user (user_lastname, user_firstname, user_mail, user_password, user_adress, user_ZIP, user_phone, user_type) " +
                        "VALUES ('" + addlname.getText() + "', '" + addfname.getText() + "', '" + addlog.getText() + "', '" + addpass.getText() + "', '" + address.getText() + "', '" + zip.getText() + "', '" + phone.getText() + "', 'normal')";
                Statement stm = co.createStatement();
                stm.executeUpdate(request);
                val = "ok";
            }
            else{
                val = "Wrong password";
            }
        }
        return val;
    }
}
