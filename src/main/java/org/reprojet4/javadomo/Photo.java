package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Photo {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
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
