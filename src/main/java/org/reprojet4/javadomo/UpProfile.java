package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpProfile {
    boolean check = true;
    String[] upcheck = {"lastname", "firstname", "mail", "phonenumber", "adre", "zip", "val"};
    String val;

    public String[] UpProfile(int id, JTextField addlname, JTextField addfname, JTextField addlog, JTextField address, JTextField zip, JTextField phone, JPasswordField addpass, JPasswordField repass, Connection co, String uppass) throws SQLException {
        check = true;
        System.out.println(addpass.getText());
        System.out.println(repass.getText());
        if (uppass == "yes") {
            if (check == true) {
                if (addpass.getText().isEmpty() || repass.getText().isEmpty()) {
                    val = "Complete info";
                    upcheck[6] = val;
                    check = false;
                }
            }
            if (check == true) {
                String request = "UPDATE personal_user " +
                        "SET user_password = '" + addpass.getText() + "'" +
                        "WHERE user_id = '" + id + "'";
                Statement stm = co.createStatement();
                stm.executeUpdate(request);
                val = "ok";
                upcheck[6] = val;
            }
        }
        else {
            if (check == true) {
                if (addlname.getText().isEmpty() || addfname.getText().isEmpty() || addlog.getText().isEmpty() || address.getText().isEmpty() || zip.getText().isEmpty() || phone.getText().isEmpty()) {
                    val = "Complete all info";
                    upcheck[6] = val;
                    check = false;
                }
            }
            if (check == true) {
                String request = "UPDATE personal_user " +
                        "SET user_lastname = '" + addlname.getText() + "', user_firstname = '" + addfname.getText() + "', user_mail = '" + addlog.getText() + "', user_phone = '" + phone.getText() + "', user_adress = '" + address.getText() + "', user_ZIP = '" + zip.getText() + "'" +
                        "WHERE user_id = '" + id + "'";
                Statement stm = co.createStatement();
                stm.executeUpdate(request);
                String req = "SELECT user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP FROM personal_user WHERE user_id = '" + id + "'";
                Statement state = co.createStatement();
                ResultSet rslt = state.executeQuery(req);
                if (rslt.next()) {
                    upcheck[0] = rslt.getString(1);
                    upcheck[1] = rslt.getString(2);
                    upcheck[2] = rslt.getString(3);
                    upcheck[3] = rslt.getString(4);
                    upcheck[4] = rslt.getString(5);
                    upcheck[5] = rslt.getString(6);
                    val = "ok";
                    upcheck[6] = val;
                }
            }
        }
        return upcheck;
    }

    public void DelProfile(int id, Connection co) throws SQLException {
        String request = "DELETE FROM personal_user WHERE user_id = '" + id + "'";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
