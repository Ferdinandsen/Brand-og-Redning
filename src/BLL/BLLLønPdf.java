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
    private Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    ArrayList<BEAppearance> localAppearances;
//    private int amount = 0;
    BEAlarm localAlarm;
    BLLUsage bllUsage;
    BLLAppearance bllAppearance;
    BELogin localLog;
    BLLVehicle bllVehicle;
    ArrayList<BEFireman> usedFiremen = new ArrayList<>();
    ArrayList<BEAppearance> firemanAppearances = new ArrayList<>();

    public BLLLønPdf(ArrayList<BEAppearance> appearances) throws DocumentException, FileNotFoundException, IOException {
        System.out.println("er nu i lønPDF");
        localAppearances = appearances;
        bllAppearance = BLLAppearance.getInstance();
        bllVehicle = BLLVehicle.getInstance();
        bllUsage = BLLUsage.getInstance();
        for (BEAppearance appearance : localAppearances) {
            System.out.println("her..");
            if (!usedFiremen.contains(appearance.getFireman())) {
                usedFiremen.add(appearance.getFireman());
                System.out.println("tilføjede: " + appearance.getFireman().getMedarbjeder().getFornavn());
            }
        }

        for (BEFireman fireman : usedFiremen) {
            for (BEAppearance appearance : localAppearances) {
                if (appearance.getFireman() == fireman) {
                    firemanAppearances.add(appearance);
                }
            }
            System.out.println("laver PDF");
            FILE = System.getProperty("user.home") + "/Desktop/" + fireman.getMedarbjeder().getFornavn() + fireman.getMedarbjeder().getEfternavn() + ".pdf";
            Document document = new Document(PageSize.A4.rotate()); //Roterer siden til at være landskab! Fjern parameter for at gøre den til normal  Document document = new Document(PageSize.LETTER.rotate()); 
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            document.close();

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

        p = (new Paragraph("Set af: " + localLog.getMedarbejder() + ", " + localAlarm.getIlGodkendtTidTimeString(), titleFont));
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
        Anchor anchor = new Anchor("Løn", catFont);
        anchor.setName("Løn");

        createFremmødeTable(document);
        document.newPage();

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
        insertDataIntoFremmødeTable(table);
        doc.add(table);
    }

    private void insertDataIntoFremmødeTable(PdfPTable table) {

        for (BEAppearance app : firemanAppearances) {
            PdfPCell c1 = new PdfPCell(new Phrase(app.getFireman().getMedarbjeder().getFornavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(app.getFireman().getMedarbjeder().getEfternavn()));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            c1 = new PdfPCell(new Phrase(app.getAlarm().getTimeString()));
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

            c1 = new PdfPCell(new Phrase(app.getTotalTid()));
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
