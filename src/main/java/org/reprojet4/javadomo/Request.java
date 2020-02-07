package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Request {
    JScrollPane DTable = new JScrollPane();

    AmpConnect ampConnect = new AmpConnect();
    CamInstall camInstall = new CamInstall();
    Datamp datamp = new Datamp();
    Datatemp datatemp = new Datatemp();
    Food food = new Food();
    PersonalUser personalUser = new PersonalUser();
    Photo photo = new Photo();
    Room room = new Room();
    Sensor sensor = new Sensor();
    ThermoIntel thermoIntel = new ThermoIntel();

    public JScrollPane Request(int id, String role, Connection co, int table, int orderby){
        DTable.removeAll();
        DTable.revalidate();
        DTable.repaint();
        switch (table){
            case 0:
                DTable = ampConnect.Request(co, orderby);
                break;
            case 1:
                DTable = camInstall.Request(co, orderby);
                break;
            case 2:
                DTable = datamp.Request(co, orderby);
                break;
            case 3:
                DTable = datatemp.Request(co, orderby);
                break;
            case 4:
                DTable = food.Request(co, orderby);
                break;
            case 5:
                DTable = personalUser.Request(co, orderby);
                break;
            case 6:
                DTable = photo.Request(co, orderby);
                break;
            case 7:
                DTable = room.Request(co, orderby);
                break;
            case 8:
                DTable = sensor.Request(co, orderby);
                break;
            case 9:
                DTable = thermoIntel.Request(co, orderby);
                break;
            default:
                break;
        }
        return DTable;
    }

    String value1;
    String value2;
    String value3;
    String value4;
    String value5;
    String value6;
    String value7;
    String value8;

    public void Insert(int id, String role, Connection co, int table, JTable tableAdd, int selection1, int selection2, int selection3, int selection4) throws SQLException {
        switch (table){
            case 0:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                ampConnect.Insert(value1, value2, selection1, selection2, co);
                break;
            case 1:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                camInstall.Insert(value1, value2, selection1, selection2, co);
                break;
            case 2:
                datamp.Insert(selection1, selection2, co);
                break;
            case 3:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                datatemp.Insert(selection1, value1, co);
                break;
            case 4:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                value3 = tableAdd.getModel().getValueAt(0, 2).toString();
                food.Insert(selection1, value1, value2, value3, co);
                break;
            case 5:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                value3 = tableAdd.getModel().getValueAt(0, 2).toString();
                value4 = tableAdd.getModel().getValueAt(0, 3).toString();
                value5 = tableAdd.getModel().getValueAt(0, 4).toString();
                value6 = tableAdd.getModel().getValueAt(0, 5).toString();
                value7 = tableAdd.getModel().getValueAt(0, 6).toString();
                personalUser.Insert(value1, value2, value3, value4, value5, value6, value7, selection1, co);
                break;
            case 6:
                // Use ListItem
                break;
            case 7:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                room.Insert(id, value1, value2, co);
                break;
            case 8:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                value3 = tableAdd.getModel().getValueAt(0, 2).toString();
                value4 = tableAdd.getModel().getValueAt(0, 3).toString();
                sensor.Insert(value1, value2, value3, value4, selection1, selection2, co);
                break;
            case 9:
                value1 = tableAdd.getModel().getValueAt(0, 0).toString();
                value2 = tableAdd.getModel().getValueAt(0, 1).toString();
                thermoIntel.Insert(selection1, selection2, selection3, selection4, value1, value2, co);
                break;
            default:
                break;
        }
    }
}
