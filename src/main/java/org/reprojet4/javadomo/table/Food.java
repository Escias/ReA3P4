package org.reprojet4.javadomo.table;

public class Food {

    public void Request(int id){
        String request = "SELECT food_id, R.room_name, food_name, food_perempt, food_quantity " +
                "FROM food AS F " +
                "LEFT JOIN room AS R " +
                "ON R.room_id = F.food_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY food_id ASC;";
        String[] t = {"id", "salle", "nom", "péremption", "quantité"};
    }

    public void Update(int id){

    }

    public void Insert(int id){

    }

    public void Delete(int id){

    }
}
