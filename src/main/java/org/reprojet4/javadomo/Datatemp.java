package org.reprojet4.javadomo;

public class Datatemp {

    public void Request(int id){
        String request = "SELECT datatemp_id, S.sensor_name, datatemp_temp, datatemp_time " +
                "FROM datatemp AS D " +
                "LEFT JOIN sensor AS S " +
                "ON S.sensor_id = D.datatemp_sensor_id " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = S.sensor_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY datatemp_id ASC;";
        String[] t = {"id", "capteur", "température", "date et heure"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
