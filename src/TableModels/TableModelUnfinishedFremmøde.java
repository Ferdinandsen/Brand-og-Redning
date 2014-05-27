package TableModels;

import BE.BEAlarm;
import BE.BEAppearance;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class TableModelUnfinishedFremmøde extends AbstractTableModel implements ITableObserver {

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAlarm> alarms;
    private String[] colNames = {
        "Titel",
        "Beskrivelse",
        "Tid",
        "Godkendt tid"};

    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        String.class, //Køretøj
        String.class,//Fornavn
        Timestamp.class,
        Timestamp.class
    };

    /**
     *
     * @param allAlarms
     */
    public TableModelUnfinishedFremmøde(ArrayList<BEAlarm> allAlarms) {
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
            case 3:
                return a.getHlGodkendtTid();
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

    public void setAlarmList(ArrayList<BEAlarm> alarm) {
        this.alarms = alarm;
    }

    public BEAlarm getAlarmByRow(int row) {
        return alarms.get(row);
    }

    @Override
    public void update() {
        fireTableDataChanged();
    }
}
