package BLL;

import BE.BEAlarm;
import BE.BEAppearance;
import BE.BELogin;
import BE.BEUsage;
import BE.BEVehicle;
import java.io.FileOutputStream;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLOdinPdf{

    private String FILE;
    private Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.NORMAL, BaseColor.RED);
    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    ArrayList<BEAppearance> localAppearances;
    private int amount = 0;
    BEAlarm localAlarm;
    BLLUsage bllUsage;
    BLLAppearance bllAppearance;
    BELogin localLog;
    BLLVehicle bllVehicle;

    public BLLOdinPdf(BEAlarm alarm, BELogin log) {
        try {
            localLog = log;
            localAlarm = alarm;
            bllAppearance = BLLAppearance.getInstance();
            bllVehicle = BLLVehicle.getInstance();
            bllUsage = BLLUsage.getInstance();
            localAppearances = bllAppearance.getAllHlGodkendtAppearances(localAlarm);
            amount = localAppearances.size();
            FILE = System.getProperty("user.home") + "/Desktop/" + localAlarm.getEvaNo() + " " + localAlarm.getDesc() + " - " + localAlarm.getIlGodkendtTid().getDate() + "-" + (localAlarm.getIlGodkendtTid().getMonth() + 1) + "-" + (localAlarm.getIlGodkendtTid().getYear() + 1900) + ".pdf";
            Document document = new Document(PageSize.A4.rotate()); //Roterer siden til at være landskab! Fjern parameter for at gøre den til normal  Document document = new Document(PageSize.LETTER.rotate()); 
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            try {
                addTitlePage(document);
            } catch (IOException ex) {
                Logger.getLogger(BLLOdinPdf.class.getName()).log(Level.SEVERE, null, ex);
            }
            addContent(document);
            document.close();
        } catch (DocumentException | FileNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Tidsregistrering");
        document.addSubject("Tidsregistrering");
        document.addKeywords("Tidsregistrering");
        document.addAuthor("Jacob, Jakob og André Thy");
        document.addCreator("Jacob, Jakob og André Thy");
    }

    private void addTitlePage(Document document)
            throws DocumentException, IOException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        Image image1 = Image.getInstance("brandogredning.jpg");
        preface.add(image1);
        addEmptyLine(preface, 1);
        // Lets write a big header
        addEmptyLine(preface, 1);
        Paragraph p = new Paragraph("Brand & Redning, Esbjerg - Station 4.24 \t \t \t ");
        p.setAlignment(Element.ALIGN_LEFT);
        preface.add(p);

        p = (new Paragraph("Set af: " + localLog.getMedarbejder() + ", " + localAlarm.getIlGodkendtTidTimeString() + " - " + localAlarm.getIlGodkendtTid().getDate() + "-" + (localAlarm.getIlGodkendtTid().getMonth() + 1), titleFont));
        p.setAlignment(Element.ALIGN_RIGHT);
        preface.add(p);
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Alarm beskrivelse: " + localAlarm.getDesc()));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Alarm Tidspunkt: " + localAlarm.getTime()));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Alarm EVA NR: " + localAlarm.getEvaNo()));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Holdleder bemærkning: " + localAlarm.getHlBemærkning()));
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Indsatsleder bemærkning: " + localAlarm.getIlBemærkning()));
        addEmptyLine(preface, 1);
        document.add(preface);
        // Start a new page
//        document.newPage();
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Fremmøde", catFont);
        anchor.setName("Fremmøde");
        Paragraph preface = new Paragraph();
        createFremmødeTable(document);
        addEmptyLine(preface, 1);
        document.add(preface);
        addEmptyLine(new Paragraph(""), 1);
        createForbrugTable(document);
        addEmptyLine(preface, 1);
        document.add(preface);
        addEmptyLine(new Paragraph(""), 1);
        createIndsatsStyrkeTable(document);

    }

    private void createIndsatsStyrkeTable(Document doc) throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell c1 = new PdfPCell(new Phrase("Vogn nr:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kørsels 1 / 2"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Bemanding:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        table.setHeaderRows(1);

        insertDataIntoIndsatsStyrkeTable(table);
        doc.add(table);
    }

    private void insertDataIntoIndsatsStyrkeTable(PdfPTable table) {
        for (BEVehicle vehicle : bllVehicle.getAllVehiclesFromAppearances(bllAppearance.getAllHlGodkendtAppearances(localAlarm))) {
            int kørselType = 0;
            int bemandingAmount = 0;
            for (BEAppearance appearance : bllAppearance.getAllHlGodkendtAppearances(localAlarm)) {
                if (appearance.getVeh() == vehicle) {
                    kørselType = appearance.getKørselsType();
                    bemandingAmount++;
                }
            }

            PdfPCell c1 = new PdfPCell(new Phrase(vehicle.toString()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            if (kørselType == 3) {
                c1 = new PdfPCell(new Phrase("Ikke i brug"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            } else {
                c1 = new PdfPCell(new Phrase(String.valueOf(kørselType)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }
            c1 = new PdfPCell(new Phrase(String.valueOf(bemandingAmount)));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }

    }

    private void createForbrugTable(Document doc) throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

        PdfPCell c1 = new PdfPCell(new Phrase("Brandmateriel:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Antal:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Brandmateriel:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Antal:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        table.setHeaderRows(1);

        insertDataIntoForbrugTable(table);
        doc.add(table);
    }

    private void insertDataIntoForbrugTable(PdfPTable table) {
        for (BEUsage usage : bllUsage.getAllUsagesForAlarm(localAlarm)) {

            PdfPCell c1 = new PdfPCell(new Phrase(usage.getMateriel().getName()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(usage.getAmount())));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        if (bllUsage.getAllUsagesForAlarm(localAlarm).size() % 2 == 1) {
            PdfPCell c1 = new PdfPCell(new Phrase(""));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            table.addCell(c1);
        }
    }

    private void createFremmødeTable(Document doc) throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(6 + bllVehicle.getAllVehiclesFromAppearances(bllAppearance.getAllHlGodkendtAppearances(localAlarm)).size());
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);

        PdfPCell c1 = new PdfPCell(new Phrase("Grad:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fornavn:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Efternavn:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tidsrum:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kørt Timer:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ST Vagt:"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        for (BEVehicle veh : bllVehicle.getAllVehiclesFromAppearances(bllAppearance.getAllHlGodkendtAppearances(localAlarm))) {
            c1 = new PdfPCell(new Phrase(veh.toString()));
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(c1);
        }

        table.setHeaderRows(1);
        int counter = 0;
        for (int i = 0; i < amount; i++) {
            if (i % 2 == 0 && counter == 5) {
                table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                counter = 0;
            } else {
                table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                counter++;
            }
        }
        insertDataIntoFremmødeTable(table);
        doc.add(table);
    }

    private void insertDataIntoFremmødeTable(PdfPTable table) {
        for (BEAppearance appearance : localAppearances) {
            String xOrNot = "X";
            if (appearance.isHoldleder()) {
                xOrNot = "HL";
            }
            if (appearance.isChauffør()) {
                xOrNot = "CH";
            }

            if (appearance.getFireman().isHoldleder()) {
                PdfPCell c1 = new PdfPCell(new Phrase("HL"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            } else {
                PdfPCell c1 = new PdfPCell(new Phrase("BM"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }
            PdfPCell c1 = new PdfPCell(new Phrase(appearance.getFireman().getMedarbjeder().getFornavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(appearance.getFireman().getMedarbjeder().getEfternavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(appearance.getAlarm().getTimeString() + "-" + appearance.getCheckOutString()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(String.valueOf(appearance.getTotalTid())));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            if (appearance.isSTvagt()) {
                c1 = new PdfPCell(new Phrase("X"));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            } else {
                c1 = new PdfPCell(new Phrase(""));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }

            for (BEVehicle veh : bllVehicle.getAllVehiclesFromAppearances(bllAppearance.getAllHlGodkendtAppearances(localAlarm))) {
                if (appearance.getVeh() != null && appearance.getVeh().getOdinnummer() == veh.getOdinnummer()) {
                    c1 = new PdfPCell(new Phrase(xOrNot));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);
                } else {
                    c1 = new PdfPCell(new Phrase(""));
                    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(c1);
                }
            }
        }
    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
