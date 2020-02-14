package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Datamp {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();
    boolean check = true;
    String order;

    /**
     * SELECT request
     * @param co
     * @param orderby
     * @return
     */
    public JTable Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "datamp_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "A.amp_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "datamp_action";
                }
                break;
            case 3:
                if (check == true){
                    order = "datamp_datetime";
                }
                break;
        }
        String request = "SELECT datamp_id, A.amp_name, datamp_action, datamp_datetime " +
                "FROM datamp AS D " +
                "LEFT JOIN ampconnect AS A " +
                "ON A.amp_id = D.datamp_amp_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                " ORDER BY "+ order +" ASC;";
        String[] t = {"id", "bulb", "action", "date and hour"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    List<String> ls = new ArrayList<>();
    String stat;

    /**
     * INSERT request
     * @param selection1
     * @param selection2
     * @param co
     * @throws SQLException
     */
    public void Insert(int selection1, int selection2, Connection co) throws SQLException {
        Integer obj1 = selection2;
        if (obj1.equals(1)){
            stat = "allumer";
        }else if (obj1.equals(2)){
            stat = "éteindre";
        }
        String request = "INSERT INTO datamp (datamp_amp_id, datamp_action, datamp_datetime) " +
                "VALUES ('"+selection1+"', '"+stat+"', NOW())";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
    /**
     * Get bulb's name
     * @param id
     * @param co
     * @param role
     * @return
     * @throws SQLException
     */
    public List InsertAdd(int id, Connection co, String role) throws SQLException {
        if (role.equals("admin")) {
            String request = "SELECT amp_id, amp_name " +
                    "FROM ampconnect AS A " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = A.amp_room_id;";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()) {
                ls.add(rslt.getString(2));
            }
        } else {
            String request = "SELECT amp_id, amp_name " +
                    "FROM ampconnect AS A " +
                    "LEFT JOIN room AS R " +
                    "ON R.room_id = A.amp_room_id " +
                    "WHERE R.room_user_id = " + id + ";";
            Statement stm = co.createStatement();
            ResultSet rslt = stm.executeQuery(request);
            while (rslt.next()) {
                ls.add(rslt.getString(2));
            }
        }
        return ls;
    }

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM datamp WHERE datamp_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }
}
