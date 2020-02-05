package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Photo {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(Connection co, int orderby){
        String request = "SELECT photo_id, cam_name, photo_image, photo_date " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                " ORDER BY room_name ASC;";
        String[] t = {"id", "nom", "chemin", "date"};
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
