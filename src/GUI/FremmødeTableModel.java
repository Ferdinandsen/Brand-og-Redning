package GUI;

import BE.BETime;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class FremmødeTableModel extends AbstractTableModel {
   
    /**
     * the names of the columns in the table
     */
    private ArrayList<BETime> times;
    private String[] colNames = {
        "Køretøj:",
        "Last name",
        "Phone",
        "Bad payer",
        "Street name",
        "Street number",
        "Floor",
        "Apartment",
        "Zip",
        "City"};

/**
 * the type definition for the columns 
 */
    private Class[] classes = {
        String.class,
        String.class,
        Integer.class,
        Boolean.class,
        String.class,
        Integer.class,
        String.class,
        String.class,
        Integer.class,
        String.class
    };
    
/**
 * 
 * @param allTimes
 */
    public FremmødeTableModel(ArrayList<BETime> allTimes) {
        times = allTimes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return times.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        BETime t = times.get(row);
        switch (col) {
            case 0:
//                return t.get();
//            case 1:
//                return t.getLASTNAME();
//            case 2:
//                return t.getPHONE();
//            case 3:
//                return t.isBADPAYER();
//            case 4:
//                return t.getADDRESS().getStreetName();
//            case 5:
//                return t.getADDRESS().getStreetNumber();
//            case 6:
//                return t.getADDRESS().getFloor();
//            case 7:
//                return t.getADDRESS().getApartment();
//            case 8:
//                return t.getADDRESS().getZipCode().getZIPCODE();
//            case 9:
//                return t.getADDRESS().getZipCode().getCITY();
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

    public void setTimeList(ArrayList<BETime> time) {
        this.times = time;
    }

    public BETime getTimeByRow(int row) {
        return times.get(row);
    }
}
