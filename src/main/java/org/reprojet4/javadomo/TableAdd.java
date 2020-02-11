package org.reprojet4.javadomo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class TableAdd {

    public JTable Tab(Connection co, String[] t, String req) {
        JTable table = new JTable();
        DefaultTableModel aModel = (DefaultTableModel) table.getModel();
        aModel.setColumnIdentifiers(t);
        String request = req;
        try {
            Statement statement = co.createStatement();
            ResultSet results = statement.executeQuery(request);
            ResultSetMetaData meta = results.getMetaData();
            int colCount = meta.getColumnCount();
            while (results.next()) {
                Object[] objects = new Object[colCount];
                for(int i = 0; i < colCount; i++) {
                    objects[i] = results.getObject(i+1);
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }
}
