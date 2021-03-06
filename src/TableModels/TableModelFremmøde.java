package TableModels;

import BE.BEAppearance;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class TableModelFremmøde extends AbstractTableModel implements ITableObserver{

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAppearance> appearances;
    private String[] colNames = {
        "Køretøj",
        "Fornavn",
        "Efternavn",
        "Alarm tid",
        "Check ud",
        "Timer",
        "Holdleder",
        "Chauffør",
        "ST Vagt"
    };
    
    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        String.class, //Køretøj
        String.class,//Fornavn
        String.class, //Efternavn
        String.class, //alarm tidspunkt
        Integer.class, // Check ud tidspunktet
        String.class, //Antal timer
        String.class, //Holdleder
        String.class, //chauffør
        String.class //ST vagt
    };

    /**
     *
     * @param allAppearances
     */
    public TableModelFremmøde(ArrayList<BEAppearance> allAppearances) {
        appearances = allAppearances;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return appearances.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        BEAppearance a = appearances.get(row);
        switch (col) {
            case 0:
                if (a.getVeh() == null) {
                    return "-";
                }
                return a.getVeh().getOdinnummer();
            case 1:
                return a.getFireman().getMedarbjeder().getFornavn();
            case 2:
                return a.getFireman().getMedarbjeder().getEfternavn();
            case 3:
                String[] alarmTime = a.getAlarm().getTime().toString().split(" ");
                return alarmTime[1];
            case 4:
                String[] checkOutTime = a.getCheckOut().toString().split(" ");
                return checkOutTime[1];
            case 5:
               return a.getTotalTid();
            case 6:
                if (a.isHoldleder()) {
                    return "X";
                }
                return "";
            case 7:
                if (a.isChauffør()) {
                    return "X";
                }
                return "";

            case 8:
                if (a.isSTvagt()) {
                    return "X";
                }
                return "";
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

    public void setAppearanceList(ArrayList<BEAppearance> appearance) {
        this.appearances = appearance;
    }

    public BEAppearance getAppearanceByRow(int row) {
        return appearances.get(row);
    }

    @Override
    public void update() {
      fireTableDataChanged();
    }
}
