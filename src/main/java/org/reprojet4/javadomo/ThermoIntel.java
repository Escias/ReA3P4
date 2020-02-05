package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class ThermoIntel {
    TableAdd tableAdd = new TableAdd();
    JScrollPane table = new JScrollPane();
    boolean check = true;
    String order;

    public JScrollPane Request(Connection co, int orderby){
        switch (orderby){
            case 0:
                if (check == true){
                    order = "thermo_id";
                }
                break;
            case 1:
                if (check == true){
                    order = "R.room_name";
                }
                break;
            case 2:
                if (check == true){
                    order = "thermo_name";
                }
                break;
            case 3:
                if (check == true){
                    order = "nom_1";
                }
                break;
            case 4:
                if (check == true){
                    order = "nom_2";
                }
                break;
            case 5:
                if (check == true){
                    order = "thermo_temp_target";
                }
                break;
            case 6:
                if (check == true){
                    order = "thermo_status";
                }
                break;
        }
        String request = "SELECT thermo_id, R.room_name, thermo_name, thermo_temp_target, thermo_status, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_1) as nom_1, " +
                "(SELECT sensor_name FROM sensor as S WHERE S.sensor_id = T.thermo_id_2) as nom_2 " +
                "FROM thermointel as T " +
                "LEFT JOIN sensor as S " +
                "ON T.thermo_name = S.sensor_name " +
                "LEFT JOIN room as R " +
                "ON R.room_id = T.thermo_room_id " +
                " ORDER BY " + order +" ASC;";
        String[] t = {"id", "room", "name", "temp. target", "status"};
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
