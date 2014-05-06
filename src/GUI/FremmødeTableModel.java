package GUI;

import BE.BEAppearance;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Team Kawabunga
 */
public class FremmødeTableModel extends AbstractTableModel {

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAppearance> appearances;
    private String[] colNames = {
        "Køretøj",
        "Fornavn",
        "Efternavn",
        "Check ind",
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
        String.class,
        String.class,
        String.class,
        String.class,
        String.class,
        Integer.class,
        String.class,
        String.class,
        String.class
    };

    /**
     *
     * @param allCustomers
     */
    public FremmødeTableModel(ArrayList<BEAppearance> allAppearances) {
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
                if (a.getAlarmVehicle() == null) {
                    return "-";
                }
                return a.getAlarmVehicle().getVeh().getOdinnummer();
            case 1:
                return a.getTime().getFireman().getMedarbjeder().getFornavn();
            case 2:
                return a.getTime().getFireman().getMedarbjeder().getEfternavn();
            case 3:
                String[] checkInTime = a.getTime().getCheckIn().toString().split(" ");
                return checkInTime[1];
            case 4:
                String[] checkOutTime = a.getTime().getCheckOut().toString().split(" ");
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
}
