package GUI;

import BE.BEAppearance;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FremmødeTableModel extends AbstractTableModel {

    /**
     * the names of the columns in the table
     */
    private ArrayList<BEAppearance> appearances;
    private String[] colNames = {
        "Køretøj",
        "Grad",
        "Fornavn",
        "Efternavn",
        "Check ind",
        "Check ud",
        "Timer",
        "Holdleder",
        "Chauffør",
    };
    /**
     * the type definition for the columns
     */
    private Class[] classes = {
        Integer.class,
        boolean.class,
        String.class,
        String.class,
        Timestamp.class,
        Timestamp.class,
        Integer.class,
        Boolean.class,
        Boolean.class,
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
                return a.getVehicle().getOdinnummer();
            case 1:
                return a.getTime().getFireman().isHoldleder();
            case 2:
                return a.getTime().getFireman().getMedarbjeder().getFornavn();
            case 3:
                return a.getTime().getFireman().getMedarbjeder().getEfternavn();
            case 4:
                return a.getTime().getCheckIn();
            case 5:
                return a.getTime().getCheckOut();
            case 6:
                return a.getTotalTid();
            case 7:
                return a.isHoldleder();
            case 8:
                return a.isChauffør();

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
