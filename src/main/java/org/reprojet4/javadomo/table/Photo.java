package org.reprojet4.javadomo.table;

public class Photo {

    public void Request(int id){
        String request = "SELECT photo_id, cam_name, photo_image, photo_date " +
                "FROM photo AS P " +
                "LEFT JOIN caminstall AS C " +
                "ON C.cam_id = P.photo_cam_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = C.cam_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY room_name ASC;";
        String[] t = {"id", "nom", "chemin", "date"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
