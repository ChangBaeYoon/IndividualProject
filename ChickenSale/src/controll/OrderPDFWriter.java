package controll;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.OrderVO;

public class OrderPDFWriter {
	public void pDFWriter(List<OrderVO> list, int check) {
		try {
			// pdf document ����.
			// (Rectangle pageSize, float marginLeft, float marginRight, float marginTop,
			// float marginBottom)
			Document document = new Document(PageSize.A4, 0, 0, 30, 30);
			// pdf ������ ������ ������ ����. pdf������ �����ȴ�. ���� ��Ʈ������ ����.
			String strReportPDFName = "order_" + System.currentTimeMillis() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(ConnectionData.writerPath + "\\" + strReportPDFName));
			// document�� ���� pdf������ �����ֵ����Ѵ�..
			document.open();
			// �ѱ�������Ʈ ����..
			BaseFont bf = BaseFont.createFont("font/MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 8, Font.NORMAL);
			Font fontTitle = new Font(bf, 14, Font.BOLD);
			// Ÿ��Ʋ
			Paragraph title = new Paragraph("�ֹ� ���", fontTitle);
			// �߰�����
			title.setAlignment(Element.ALIGN_CENTER);
			// ������ �߰�
			document.add(title);
			document.add(new Paragraph("\r\n"));
			// ���� ��¥
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM�� dd�� E����");
			Paragraph writeDay = new Paragraph(sdf.format(date)+"                     ", font);
			// ������ ����
			writeDay.setAlignment(Element.ALIGN_RIGHT);
			// ������ �߰�
			document.add(writeDay);
			document.add(new Paragraph("\r\n"));

			// ���̺���� Table��ü���� PdfPTable��ü�� �� �����ϰ� ���̺��� ����� �ִ�.
			// �����ڿ� �÷����� ���ش�.
			PdfPTable table = new PdfPTable(6);
			// ������ �÷��� width�� ���Ѵ�.
			table.setWidths(new int[] { 120, 150, 250, 300, 100, 80 });

			// �÷� Ÿ��Ʋ..
			PdfPCell header1 = new PdfPCell(new Paragraph("�ֹ���", font));
			PdfPCell header2 = new PdfPCell(new Paragraph("��ȭ��ȣ", font));
			PdfPCell header3 = new PdfPCell(new Paragraph("���ּ�", font));
			PdfPCell header4 = new PdfPCell(new Paragraph("�ֹ�����", font));
			PdfPCell header5 = new PdfPCell(new Paragraph("�Ǹűݾ�", font));
			PdfPCell header6 = new PdfPCell(new Paragraph("����", font));

			// ��������
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header5.setHorizontalAlignment(Element.ALIGN_CENTER);
			header6.setHorizontalAlignment(Element.ALIGN_CENTER);

			// ��������
			header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// ���̺� �߰�..
			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);
			table.addCell(header4);
			table.addCell(header5);
			table.addCell(header6);

			// DB ���� �� ����Ʈ ����
			try {
				OrderVO oVo = new OrderVO();
				
				int rowCount = list.size();

				PdfPCell cell1 = null;
				PdfPCell cell2 = null;
				PdfPCell cell3 = null;
				PdfPCell cell4 = null;
				PdfPCell cell5 = null;
				PdfPCell cell6 = null;

				for (int index = 0; index < rowCount; index++) {

					oVo = list.get(index);

					cell1 = new PdfPCell(new Paragraph(oVo.getDate() + "", font));
					cell2 = new PdfPCell(new Paragraph(oVo.getPhone(), font));
					cell3 = new PdfPCell(new Paragraph(oVo.getAddress(), font));
					cell4 = new PdfPCell(new Paragraph(oVo.getContents(), font));
					cell5 = new PdfPCell(new Paragraph(oVo.getPrice(), font));
					cell6 = new PdfPCell(new Paragraph(oVo.getCash() + "", font));

					// ��������
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

					// ��������
					cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

					// ���̺� �� �߰�
					table.addCell(cell1);
					table.addCell(cell2);
					table.addCell(cell3);
					table.addCell(cell4);
					table.addCell(cell5);
					table.addCell(cell6);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// ������ ���̺��߰�..
			document.add(table);
			
			document.add(new Paragraph("\r\n"));
			Alert alert = new Alert(AlertType.INFORMATION);
			
			if (check == 1) {
				//���� �׷����� �� �׷��� ����
				// ���� �׷��� �̹��� �߰�
				Paragraph barImageTitle = new Paragraph("�ֹ� ���� �׷���", font);
				barImageTitle.setAlignment(Element.ALIGN_CENTER);
				document.add(barImageTitle);
				document.add(new Paragraph("\r\n"));
				final String barImageUrl = "chartImage/orderBarChart.png";
				// ������ javafx.scene.image.Image ��ü�� ����ϰ� �־� �浹�� ���� �Ʒ���
				// ���� �����.
				com.itextpdf.text.Image barImage;
				try {
					if (com.itextpdf.text.Image.getInstance(barImageUrl) != null) {
						barImage = com.itextpdf.text.Image.getInstance(barImageUrl);
						barImage.setAlignment(Element.ALIGN_CENTER);
						barImage.scalePercent(30f);
						document.add(barImage);
						document.add(new Paragraph("\r\n"));
					}
				} catch (IOException ee) {

				}

				// ���� �׷��� �̹��� �߰�
				Paragraph pieImageTitle1 = new Paragraph("�ֹ� ���� �׷���", font);
				pieImageTitle1.setAlignment(Element.ALIGN_CENTER);
				document.add(pieImageTitle1);
				document.add(new Paragraph("\r\n"));
				final String pieImageUrl = "chartImage/orderPieChart.png";
				com.itextpdf.text.Image pieImage;
				try {
					if (com.itextpdf.text.Image.getInstance(pieImageUrl) != null) {
						pieImage = com.itextpdf.text.Image.getInstance(pieImageUrl);
						pieImage.setAlignment(Element.ALIGN_CENTER);
						pieImage.scalePercent(30f);
						document.add(pieImage);
					}
				} catch (IOException ee) {

				}

			} else if (check == 2) {
				//���� �׷��� ����
				// ���� �׷��� �̹��� �߰�
				Paragraph barImageTitle = new Paragraph("�ֹ� ���� �׷���", font);
				barImageTitle.setAlignment(Element.ALIGN_CENTER);
				document.add(barImageTitle);
				document.add(new Paragraph("\r\n"));
				final String barImageUrl = "chartImage/orderBarChart.png";
				com.itextpdf.text.Image barImage;
				try {
					if (com.itextpdf.text.Image.getInstance(barImageUrl) != null) {
						barImage = com.itextpdf.text.Image.getInstance(barImageUrl);
						barImage.setAlignment(Element.ALIGN_CENTER);
						barImage.scalePercent(30f);
						document.add(barImage);
					}
				} catch (IOException ee) {

				}

			} else if (check == 3) {
				//�� �׷��� ����
				// ���� �׷��� �̹��� �߰�
				Paragraph pieImageTitle1 = new Paragraph("�ֹ� ���� �׷���", font);
				pieImageTitle1.setAlignment(Element.ALIGN_CENTER);
				document.add(pieImageTitle1);
				document.add(new Paragraph("\r\n"));
				final String pieImageUrl = "chartImage/orderPieChart.png";
				com.itextpdf.text.Image pieImage;
				try {
					if (com.itextpdf.text.Image.getInstance(pieImageUrl) != null) {
						pieImage = com.itextpdf.text.Image.getInstance(pieImageUrl);
						pieImage.setAlignment(Element.ALIGN_CENTER);
						pieImage.scalePercent(30f);
						document.add(pieImage);
					}
				} catch (IOException ee) {

				}
			}
			
			
			// ������ �ݴ´�.. ���� ����..
			document.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
