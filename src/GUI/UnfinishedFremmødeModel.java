package GUI;

import BE.BEAlarm;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class UnfinishedFremmødeModel extends AbstractTableModel {

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAlarm> alarms;
    private String[] colNames = {
        "Titel",
        "Beskrivelse",
        "Tid",};
    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        String.class, //Køretøj
        String.class,//Fornavn
        Timestamp.class
    };

    /**
     *
     * @param allAlarms
     */
    public UnfinishedFremmødeModel(ArrayList<BEAlarm> allAlarms) {
        alarms = allAlarms;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return alarms.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        BEAlarm a = alarms.get(row);
        switch (col) {
            case 0:
                
                return a.getTitle();
            case 1:
                return a.getDesc();
            case 2:
                return a.getTime();
            
        }
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void setAppearanceList(ArrayList<BEAlarm> alarm) {
        this.alarms = alarm;
    }

    public BEAlarm getAppearanceByRow(int row) {
        return alarms.get(row);
    }
}
