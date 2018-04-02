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
 * �ֿ� ������ �Ʒ� �۾��� ���ؼ� �ʿ��� ��ŭ Sheet, Row, Cell�� ���� �Ͽ� FileOutputStream���� ����ó�� �ϸ� �ȴ�.

    xls : HSSFWorkbook����->HSSFSheet����->HSSFRow����->HSSFCell���� 
    xlsx : XSSFWorkbook����->XSSFSheet����->XSSFRow����->XSSFCell����
*/

public class OrderExcelWriter {
	public void xlsWiter(List<OrderVO> list) {
		// ��ũ�� ����
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ��ũ��Ʈ ����
		XSSFSheet sheet = workbook.createSheet();
		// �� ����
		XSSFRow row = sheet.createRow(0);
		// �� ����
		XSSFCell cell;

		// ��� ���� ����
		cell = row.createCell(0);
		cell.setCellValue("�ֹ���ȣ");

		cell = row.createCell(1);
		cell.setCellValue("�ֹ���¥");

		cell = row.createCell(2);
		cell.setCellValue("��ȭ��ȣ");

		cell = row.createCell(3);
		cell.setCellValue("���ּ�");
		
		cell = row.createCell(4);
		cell.setCellValue("�ֹ�����");
		
		cell = row.createCell(5);
		cell.setCellValue("�Ǹűݾ�");
		
		cell = row.createCell(6);
		cell.setCellValue("����");

		// ����Ʈ�� size ��ŭ row�� ����
		OrderVO vo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			vo = list.get(rowIdx);

			// �� ����
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

		// �Էµ� ���� ���Ϸ� ����
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
		// ��ũ�� ����
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ��ũ��Ʈ ����
		XSSFSheet sheet = workbook.createSheet();
		// �� ����
		XSSFRow row = sheet.createRow(0);
		// �� ����
		XSSFCell cell;

		// ��� ���� ����
		cell = row.createCell(0);
		cell.setCellValue("�ֹ���ȣ");

		cell = row.createCell(1);
		cell.setCellValue("�ֹ���¥");

		cell = row.createCell(2);
		cell.setCellValue("��ȭ��ȣ");

		cell = row.createCell(3);
		cell.setCellValue("���ּ�");
		
		cell = row.createCell(4);
		cell.setCellValue("�ֹ�����");
		
		cell = row.createCell(5);
		cell.setCellValue("�Ǹűݾ�");
		
		cell = row.createCell(6);
		cell.setCellValue("����");

		// ����Ʈ�� size ��ŭ row�� ����
		OrderVO vo;
		for (int rowIdx = 0; rowIdx < list.size(); rowIdx++) {
			vo = list.get(rowIdx);

			// �� ����
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

		// �Էµ� ���� ���Ϸ� ����
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
