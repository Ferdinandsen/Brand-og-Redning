package BLL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BEFireman;
import BE.BELogin;
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class BLLLønPdf {

    private String FILE;
    private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.NORMAL, BaseColor.RED);
    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    ArrayList<BEAppearance> localAppearances = new ArrayList<>();
    BEAlarm localAlarm;
    BLLUsage bllUsage;
    BLLAppearance bllAppearance;
    BELogin localLog;
    BLLVehicle bllVehicle;
    ArrayList<BEFireman> usedFiremen = new ArrayList<>();
    ArrayList<BEAppearance> firemanAppearances = new ArrayList<>();
    int totalTimerBM = 0;
    int totalTimerHL = 0;
    int totalTimerST = 0;

    public BLLLønPdf(ArrayList<BEAppearance> appearances, BELogin log, String fromDate, String toDate) throws DocumentException, FileNotFoundException, IOException {
        localAppearances = appearances;
        localLog = log;
        bllAppearance = BLLAppearance.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllUsage = BLLUsage.getInstance();
        for (BEAppearance appearance : localAppearances) { //puts all fireman in an arraylist, and avoid duplicates
            if (!usedFiremen.contains(appearance.getFireman())) {
                usedFiremen.add(appearance.getFireman());
            }
        }

        for (BEFireman fireman : usedFiremen) { //loops through each unique fireman
            totalTimerBM = 0;
            totalTimerHL = 0;
            totalTimerST = 0;
            firemanAppearances = new ArrayList<>();
            for (BEAppearance appearance : localAppearances) { //loops through the whole list
                if (appearance.getFireman() == fireman) {
                    firemanAppearances.add(appearance); //adds the appearance to an ArrayList
                }
            }
            FILE = System.getProperty("user.home") + "/Desktop/" + fireman.getMedarbjeder().getFornavn() + fireman.getMedarbjeder().getEfternavn() + " " + fromDate + " " + toDate + ".pdf";
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            Paragraph p = new Paragraph("Total timer som BM: " + String.valueOf(totalTimerBM) + ", Total timer som ST: " + String.valueOf(totalTimerST) + ", Total timer som ST: " + String.valueOf(totalTimerHL));

            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);
            document.close();
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Løn");
        document.addSubject("Løn");
        document.addKeywords("Løn");
        document.addAuthor("Jacob, Jakob og André Thy");
        document.addCreator("Jacob, Jakob og André Thy");
    }

    private void addTitlePage(Document document)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        Image image1 = Image.getInstance("brandogredning.jpg");
        preface.add(image1);
        addEmptyLine(preface, 1);
        addEmptyLine(preface, 1);
        Paragraph p = new Paragraph("Brand & Redning, Esbjerg - Station 4.24");
        p.setAlignment(Element.ALIGN_LEFT);
        preface.add(p);

        p = (new Paragraph("Set af: " + localLog.getMedarbejder(), titleFont));
        p.setAlignment(Element.ALIGN_RIGHT);
        preface.add(p);
        addEmptyLine(preface, 1);

        document.add(preface);

    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Løn", catFont);
        anchor.setName("Løn");

        createFremmødeTable(document); //creates the table, that shows all his appearances
        addEmptyLine(new Paragraph(), 1);

    }

    private void createFremmødeTable(Document doc) throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Fornavn"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Efternavn:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Alarm tid:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tidsrum:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Funktion"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Total"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        table.setHeaderRows(1);
        insertDataIntoFremmødeTable(table, doc);
        doc.add(table);
    }

    private void insertDataIntoFremmødeTable(PdfPTable table, Document doc) throws DocumentException {

        for (BEAppearance app : firemanAppearances) {
            if (app.getFireman().isHoldleder() && !app.isSTvagt()) {
                totalTimerHL += app.getTotalTid();
            }
            if (!app.getFireman().isHoldleder() && !app.isSTvagt()) {
                totalTimerBM += app.getTotalTid();
            }
            if(app.isSTvagt()){
                totalTimerST += app.getTotalTid(); 
            }
            PdfPCell c1 = new PdfPCell(new Phrase(app.getFireman().getMedarbjeder().getFornavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(app.getFireman().getMedarbjeder().getEfternavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(app.getAlarm().getDateString() + " - " + app.getAlarm().getTimeString()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(app.getAlarm().getTimeString() + " - " + app.getCheckOutString()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            if (app.isSTvagt()) {
                c1 = new PdfPCell(new Phrase("ST"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            } else if (app.isHoldleder()) {
                c1 = new PdfPCell(new Phrase("HL"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            } else {
                c1 = new PdfPCell(new Phrase("BM"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }

            c1 = new PdfPCell(new Phrase(String.valueOf(app.getTotalTid())));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
