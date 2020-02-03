package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class ThermoIntel {
    TableAdd tableAdd = new TableAdd();
    JTable table = new JTable();

    public JTable Request(int id, Connection co, String role){
        String request = "SELECT thermo_id, R.room_name, thermo_name, thermo_temp_target, thermo_status, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_1) as nom_1, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_2) as nom_2 " +
                "FROM thermointel as T " +
                "LEFT JOIN sensor as S " +
                "ON T.thermo_name = S.sensor_name " +
                "LEFT JOIN room as R " +
                "ON R.room_id = T.thermo_room_id " +
                "WHERE R.room_user_id = " + id +
                " ORDER BY thermo_id ASC;";
        String[] t = {"id", "salle", "nom", "temp. cible", "status"};
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
