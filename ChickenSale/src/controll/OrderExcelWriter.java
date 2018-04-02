package controll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.OrderVO;


/*
 * 주요 동작은 아래 작업을 통해서 필요한 만큼 Sheet, Row, Cell을 생성 하여 FileOutputStream으로 저장처리 하면 된다.

    xls : HSSFWorkbook생성->HSSFSheet생성->HSSFRow생성->HSSFCell생성 
    xlsx : XSSFWorkbook생성->XSSFSheet생성->XSSFRow생성->XSSFCell생성
*/

public class OrderExcelWriter {
	public void xlsWiter(List<OrderVO> list) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 쎌 생성
		XSSFCell cell;

		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("주문번호");

		cell = row.createCell(1);
		cell.setCellValue("주문날짜");

		cell = row.createCell(2);
		cell.setCellValue("전화번호");

		cell = row.createCell(3);
		cell.setCellValue("고객주소");
		
		cell = row.createCell(4);
		cell.setCellValue("주문내역");
		
		cell = row.createCell(5);
		cell.setCellValue("판매금액");
		
		cell = row.createCell(6);
		cell.setCellValue("구분");

		// 리스트의 size 만큼 row를 생성
		OrderVO vo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			vo = list.get(rowIdx);

			// 행 생성
			row = sheet.createRow(rowIdx + 1);

			cell = row.createCell(0);
			cell.setCellValue(vo.getoNum());

			cell = row.createCell(1);
			cell.setCellValue(vo.getDate()+"");

			cell = row.createCell(2);
			cell.setCellValue(vo.getPhone());

			cell = row.createCell(3);
			cell.setCellValue(vo.getAddress());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getContents());
			
			cell = row.createCell(5);
			cell.setCellValue(vo.getPrice());
			
			cell = row.createCell(6);
			cell.setCellValue(vo.getCash());

		}

		// 입력된 내용 파일로 쓰기
		String path = "";
		if(ConnectionData.writerPath != null) {
			path = ConnectionData.writerPath;
		}
		File file = new File(path+"\\orderWrite"+System.currentTimeMillis()+".xls");
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void xlsxWiter(List<OrderVO> list) {
		// 워크북 생성
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 워크시트 생성
		XSSFSheet sheet = workbook.createSheet();
		// 행 생성
		XSSFRow row = sheet.createRow(0);
		// 쎌 생성
		XSSFCell cell;

		// 헤더 정보 구성
		cell = row.createCell(0);
		cell.setCellValue("주문번호");

		cell = row.createCell(1);
		cell.setCellValue("주문날짜");

		cell = row.createCell(2);
		cell.setCellValue("전화번호");

		cell = row.createCell(3);
		cell.setCellValue("고객주소");
		
		cell = row.createCell(4);
		cell.setCellValue("주문내역");
		
		cell = row.createCell(5);
		cell.setCellValue("판매금액");
		
		cell = row.createCell(6);
		cell.setCellValue("구분");

		// 리스트의 size 만큼 row를 생성
		OrderVO vo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			vo = list.get(rowIdx);

			// 행 생성
			row = sheet.createRow(rowIdx + 1);

			cell = row.createCell(0);
			cell.setCellValue(vo.getoNum());

			cell = row.createCell(1);
			cell.setCellValue(vo.getDate()+"");

			cell = row.createCell(2);
			cell.setCellValue(vo.getPhone());

			cell = row.createCell(3);
			cell.setCellValue(vo.getAddress());
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getContents());
			
			cell = row.createCell(5);
			cell.setCellValue(vo.getPrice());
			
			cell = row.createCell(6);
			cell.setCellValue(vo.getCash());

		}

		// 입력된 내용 파일로 쓰기
		String path = "";
		if(ConnectionData.writerPath != null) {
			path = ConnectionData.writerPath;
		}
		File file = new File(path+"\\orderWrite"+System.currentTimeMillis()+".xlsx");
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file);
			workbook.write(fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
