
package com.plants.services;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.plants.pojo.Plant;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class PDFManager {
    private String filePath;
    private String fileName;
    private String date;
    private ArrayList<Plant> plants;
    
    public PDFManager(){
        this.filePath = "pdf";
        this.plants = new ArrayList<Plant>();
    }
    private void pdfHandler() {
		try {
                        this.date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			File folder = new File("pdf");
			if (!folder.exists()) {
				folder.mkdir();
			}
			int num = (int) (Math.random() * 999999 + 1);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String uniqueKey = formatter.format(new Date()) + num + "_";
			this.fileName = uniqueKey +(int)(Math.random()*999999+1) +".pdf";
			
			File file = new File(this.filePath + File.separator + this.fileName);
			this.filePath = this.filePath + File.separator + this.fileName;
			file.createNewFile();

			Document document = new Document(PageSize.A4, 15f, 15f, 15f, 15f);
			FileOutputStream fout = new FileOutputStream(file);
			PdfWriter.getInstance(document, fout);
			document.open();
			Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
			Font font3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			
			Paragraph pharmacyName = new Paragraph("Plants Information", font1);
			
			Paragraph reportDate = new Paragraph("Report Date : " +this.date , font2);
	

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);

			PdfPCell cell1 = new PdfPCell(pharmacyName);
			cell1.setColspan(6);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setPadding(5);
			cell1.disableBorderSide(2);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell2 = new PdfPCell(reportDate);
			cell2.setColspan(6);
			cell2.disableBorderSide(1);
			cell2.setPadding(5);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_CENTER);

			table.addCell(cell1);
			table.addCell(cell2);


			PdfPCell cell;
			for (int i = 0; i < this.plants.size(); i++) {
                                cell = new PdfPCell(new Paragraph("SN", font2));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Common Name", font2));
				cell.setPadding(5);
                                cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Genus", font2));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Species", font2));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Location", font2));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                //Data Entry starts
				cell = new PdfPCell(new Paragraph(Integer.toString(i + 1), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(this.plants.get(i).getCommonName(), font3));
				cell.setPadding(5);
                                cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(this.plants.get(i).getGenus(), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(this.plants.get(i).getSpecies(), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(this.plants.get(i).getLocation(), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Characteristics Steam", font2));
				cell.setPadding(5);
                                cell.setColspan(3);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("Characteristics Leaf", font2));
				cell.setPadding(5);
                                cell.setColspan(3);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph(this.plants.get(i).getSteam(), font3));
				cell.setPadding(5);
                                cell.setColspan(3);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph(this.plants.get(i).getLeaf(), font3));
				cell.setPadding(5);
                                cell.setColspan(3);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                
                                cell = new PdfPCell(new Paragraph("-----------------------------------------------------------------------------------", font2));
				cell.setPadding(5);
                                cell.setColspan(6);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			document.add(table);
			document.close();
		} catch (Exception er) {
			System.out.println("Error PDF : " + er.getMessage());
		}
	}
    private void PdfViewer() {
		JFrame frame;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight());
		int width = (int) (screenSize.getWidth());
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setTitle("PDF View");
		frame.setLocationRelativeTo(null);

		SwingController controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();

		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
		frame.getContentPane().add(viewerComponentPanel);
		frame.add(viewerComponentPanel);

		controller.openDocument(this.filePath);

		frame.pack();
		frame.setVisible(true);
	}
    public boolean saveAndViewReport(){
        boolean status = true;
        PlantRegisterService PRService = new PlantRegisterService();
        this.plants = PRService.getRegisteredPlants();
        if(this.plants.size()>0){
            this.pdfHandler();
            this.PdfViewer();
        }
        else{
            status = false;
        }
        return status;
    }
}
