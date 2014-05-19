package BLL;

import BE.BEAppearance;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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
import java.util.ArrayList;

public class BLLPdf {

    private String FILE = "C:/Users/Kaj/Desktop/FirstPdf.pdf";
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

    public BLLPdf(ArrayList<BEAppearance> allHlGodkendtAppearances) {
        localAppearances = allHlGodkendtAppearances;
        amount = localAppearances.size();
        try {
            Document document = new Document(PageSize.LETTER.rotate()); //Roterer siden til at være landskab! Fjern parameter for at gøre den til normal  Document document = new Document(PageSize.LETTER.rotate()); 
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            addTitlePage(document);
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
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("Brand & Redning, Esbjerg                    Set af: " + System.getProperty("user.name") + ", " + new Date(), titleFont));
        document.add(preface);
        // Start a new page
//        document.newPage();
    }

    private void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Tidsregistrering", catFont);
        anchor.setName("Tidsregistrering");

//
//        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
//
//        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
//        Section subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Hello"));
//
//        subPara = new Paragraph("Subcategory 2", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("Paragraph 1"));
//        subCatPart.add(new Paragraph("Paragraph 2"));
//        subCatPart.add(new Paragraph("Paragraph 3"));
//
//        // add a list
//        createList(subCatPart);
//        Paragraph paragraph = new Paragraph();
//        addEmptyLine(paragraph, 5);
//        subCatPart.add(paragraph);

        // add a table
        //createHeaderTable(subCatPart);
        createGridTable(catPart);

        // now add all this to the document
        document.add(catPart);

//        // Next section
//        anchor = new Anchor("Second Chapter", catFont);
//        anchor.setName("Second Chapter");
//
//        // Second parameter is the number of the chapter
//        catPart = new Chapter(new Paragraph(anchor), 1);
//
//        subPara = new Paragraph("Subcategory", subFont);
//        subCatPart = catPart.addSection(subPara);
//        subCatPart.add(new Paragraph("This is a very important message"));
//
//        // now add all this to the document
//        document.add(catPart);

    }

//    private static void createHeaderTable(Section subCatPart)
//            throws BadElementException {
//        PdfPTable table = new PdfPTable(3);
//
//        // t.setBorderColor(BaseColor.GRAY);
//        // t.setPadding(4);
//        // t.setSpacing(4);
//        // t.setBorderWidth(1);
//
//        PdfPCell c1 = new PdfPCell(new Phrase("Fremmødeliste ved: "));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Table Header 2"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//
//        c1 = new PdfPCell(new Phrase("Table Header 3"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//        table.setHeaderRows(1);
//
//        table.addCell("1.0");
//        table.addCell("1.1");
//        table.addCell("1.2");
//        table.addCell("2.1");
//        table.addCell("2.2");
//        table.addCell("2.3");
//        
//        for (int i = 0; i < amount; i++){
//           table.addCell("TESTDATA");
//       }
//
//        subCatPart.add(table);
//
//    }
    private void createGridTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(new float[]{1, 2, 3, 3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2});
        table.setWidthPercentage(100f);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("Gr:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Grad:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Fornavn:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Efternavn:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Tidsrum:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Kørt Timer:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("ST Vagt:"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1341"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1338"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2338"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("1343"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2351"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2349"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2332"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("2341"));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);


        table.setHeaderRows(1);
        int counter = 0;
        for (int i = 0; i < amount; i++) {
            if (i % 2 == 0 && counter == 5) {
                table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
                insertData(table);
                counter = 0;
            } else {
                table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                insertData(table);
                counter++;
            }
        }
        subCatPart.add(table);
    }

    private void insertData(PdfPTable table) {
        for (BEAppearance appearance : localAppearances) {
            table.addCell("??");
            table.addCell(String.valueOf(appearance.getFireman().isHoldleder()));
            table.addCell(appearance.getFireman().getMedarbjeder().getFornavn());
            table.addCell(appearance.getFireman().getMedarbjeder().getEfternavn());
            table.addCell(appearance.getAlarm().getTimeString());
            table.addCell(String.valueOf(appearance.getTotalTid()));
            if (appearance.isSTvagt()) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null && appearance.getVeh().toString().equals("1341")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("1338")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("2338")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("1343")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("2351")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("2349")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("2332")) {
                table.addCell("X");
            } else {
                table.addCell("");
            }
            if (appearance.getVeh() != null &&appearance.getVeh().toString().equals("2341")) {
                table.addCell("X");
            } else {
                table.addCell("");
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
