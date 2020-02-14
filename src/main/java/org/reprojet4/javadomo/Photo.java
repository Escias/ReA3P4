package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Photo {
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
                    order = "photo_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "C.cam_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "photo_date";
                }
                break;
        }
        String request = "SELECT photo_id, C.cam_name, photo_image, photo_date " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "camera", "path", "date"};
        table = tableAdd.Tab(co, t, request);
        return table;
    }

    /**
     * DELETE request
     * @param id
     * @param co
     * @throws SQLException
     */
    public void Delete(int id, Connection co) throws SQLException {
        String request = "DELETE FROM photo WHERE photo_id = "+id+";";
        Statement stm = co.createStatement();
        stm.executeUpdate(request);
    }

    String imgpath;

    /**
     * Get path of image
     * @param id
     * @param co
     * @return
     * @throws SQLException
     */
    public String TakePath(int id, Connection co) throws SQLException {
        String request = "SELECT photo_id, photo_image " +
                "FROM photo " +
                "WHERE photo_id = "+id+";";
        Statement stm = co.createStatement();
        ResultSet rslt = stm.executeQuery(request);
        if (rslt.next()){
            imgpath = rslt.getString(2);
        }
        return imgpath;
    }
}
