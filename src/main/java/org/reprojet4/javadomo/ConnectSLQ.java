package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.*;

public class ConnectSLQ {
    protected String nolog;
    protected String role;
    protected String id;
    public String[] infoUser = {"id", "role", "nolog"};

    public String[] ConnectSQL(JTextField login, JPasswordField password, Connection co) throws SQLException {
        String request = "SELECT user_id, user_type FROM personal_user WHERE '" + login.getText() + "' = personal_user.user_mail AND '" + password.getText() + "' = personal_user.user_password;";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        if(rslt.next()){
            id = rslt.getString(1);
            role = rslt.getString(2);
            infoUser[0] = id;
            infoUser[1] = role;
        }else{
            nolog = "fail";
            infoUser[3] = nolog;
        }
        return infoUser;
    }
}
