package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ConnectSLQ {
    protected String nolog;
    protected String role;
    protected String id;
    public String[] infoUser = {"0", "role", "nolog", "lastname", "firstname", "mail", "phone", "address", "ZIP"};
    CreateIni createIni = new CreateIni();

    public String[] ConnectSQL(JTextField login, JPasswordField password, Connection co, JCheckBox check) throws SQLException {
        String request = "SELECT user_id, user_type, user_lastname, user_firstname, user_mail, user_phone, user_adress, user_ZIP FROM personal_user WHERE '" + login.getText() + "' = personal_user.user_mail AND '" + password.getText() + "' = personal_user.user_password;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        if(rslt.next()){
            infoUser[0] = rslt.getString(1);
            infoUser[1] = rslt.getString(2);
            infoUser[3] = rslt.getString(3);
            infoUser[4] = rslt.getString(4);
            infoUser[5] = rslt.getString(5);
            infoUser[6] = rslt.getString(6);
            infoUser[7] = rslt.getString(7);
            infoUser[8] = rslt.getString(8);
            createIni.CreateIni(login, password, check);
        }else{
            infoUser[2] = "fail";
        }
        return infoUser;
    }
}
