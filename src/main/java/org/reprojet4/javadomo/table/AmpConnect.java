package org.reprojet4.javadomo.table;

public class AmpConnect {

    public void Request(int id){
        String request = "SELECT amp_name, R.room_name, amp_status, amp_color, amp_time_on, amp_time_off " +
                "FROM ampconnect AS A " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = A.amp_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY amp_name ASC;";
        String[] t = {"name", "salle", "status", "couleur", "heure d'activation", "heure d'extinction"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
