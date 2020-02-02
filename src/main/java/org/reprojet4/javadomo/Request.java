package org.reprojet4.javadomo;

import javax.swing.*;
import java.sql.Connection;

public class Request {
    JTable DTable = new JTable();
    AmpConnect ampConnect = new AmpConnect();

    public JTable Request(int id, String role, Connection co, int table){
        DTable.removeAll();
        DTable.revalidate();
        DTable.repaint();
        switch (table){
            case 0:
                DTable = ampConnect.Request(id, co);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            default:
                break;
        }
        return DTable;
    }
}
