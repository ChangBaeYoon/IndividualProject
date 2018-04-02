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
			// pdf document 선언.
			// (Rectangle pageSize, float marginLeft, float marginRight, float marginTop,
			// float marginBottom)
			Document document = new Document(PageSize.A4, 0, 0, 30, 30);
			// pdf 파일을 저장할 공간을 선언. pdf파일이 생성된다. 그후 스트림으로 저장.
			String strReportPDFName = "order_" + System.currentTimeMillis() + ".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(ConnectionData.writerPath + "\\" + strReportPDFName));
			// document를 열어 pdf문서를 쓸수있도록한다..
			document.open();
			// 한글지원폰트 설정..
			BaseFont bf = BaseFont.createFont("font/MALGUN.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 8, Font.NORMAL);
			Font fontTitle = new Font(bf, 14, Font.BOLD);
			// 타이틀
			Paragraph title = new Paragraph("주문 목록", fontTitle);
			// 중간정렬
			title.setAlignment(Element.ALIGN_CENTER);
			// 문서에 추가
			document.add(title);
			document.add(new Paragraph("\r\n"));
			// 생성 날짜
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일");
			Paragraph writeDay = new Paragraph(sdf.format(date)+"                     ", font);
			// 오른쪽 정렬
			writeDay.setAlignment(Element.ALIGN_RIGHT);
			// 문서에 추가
			document.add(writeDay);
			document.add(new Paragraph("\r\n"));

			// 테이블생성 Table객체보다 PdfPTable객체가 더 정교하게 테이블을 만들수 있다.
			// 생성자에 컬럼수를 써준다.
			PdfPTable table = new PdfPTable(6);
			// 각각의 컬럼에 width를 정한다.
			table.setWidths(new int[] { 120, 150, 250, 300, 100, 80 });

			// 컬럼 타이틀..
			PdfPCell header1 = new PdfPCell(new Paragraph("주문일", font));
			PdfPCell header2 = new PdfPCell(new Paragraph("전화번호", font));
			PdfPCell header3 = new PdfPCell(new Paragraph("고객주소", font));
			PdfPCell header4 = new PdfPCell(new Paragraph("주문내역", font));
			PdfPCell header5 = new PdfPCell(new Paragraph("판매금액", font));
			PdfPCell header6 = new PdfPCell(new Paragraph("구분", font));

			// 가로정렬
			header1.setHorizontalAlignment(Element.ALIGN_CENTER);
			header2.setHorizontalAlignment(Element.ALIGN_CENTER);
			header3.setHorizontalAlignment(Element.ALIGN_CENTER);
			header4.setHorizontalAlignment(Element.ALIGN_CENTER);
			header5.setHorizontalAlignment(Element.ALIGN_CENTER);
			header6.setHorizontalAlignment(Element.ALIGN_CENTER);

			// 세로정렬
			header1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header2.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header3.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header4.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header5.setVerticalAlignment(Element.ALIGN_MIDDLE);
			header6.setVerticalAlignment(Element.ALIGN_MIDDLE);

			// 테이블에 추가..
			table.addCell(header1);
			table.addCell(header2);
			table.addCell(header3);
			table.addCell(header4);
			table.addCell(header5);
			table.addCell(header6);

			// DB 연결 및 리스트 선택
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

					// 가로정렬
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell6.setHorizontalAlignment(Element.ALIGN_CENTER);

					// 세로정렬
					cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

					// 테이블에 셀 추가
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

			// 문서에 테이블추가..
			document.add(table);
			
			document.add(new Paragraph("\r\n"));
			Alert alert = new Alert(AlertType.INFORMATION);
			
			if (check == 1) {
				//막대 그래프와 원 그래프 선택
				// 막대 그래프 이미지 추가
				Paragraph barImageTitle = new Paragraph("주문 막대 그래프", font);
				barImageTitle.setAlignment(Element.ALIGN_CENTER);
				document.add(barImageTitle);
				document.add(new Paragraph("\r\n"));
				final String barImageUrl = "chartImage/orderBarChart.png";
				// 기존에 javafx.scene.image.Image 객체을 사용하고 있어 충돌이 생겨 아래와
				// 같이 사용함.
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

				// 파이 그래프 이미지 추가
				Paragraph pieImageTitle1 = new Paragraph("주문 파이 그래프", font);
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
				//막대 그래프 선택
				// 막대 그래프 이미지 추가
				Paragraph barImageTitle = new Paragraph("주문 막대 그래프", font);
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
				//원 그래프 선택
				// 파이 그래프 이미지 추가
				Paragraph pieImageTitle1 = new Paragraph("주문 파이 그래프", font);
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
			
			
			// 문서를 닫는다.. 쓰기 종료..
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
