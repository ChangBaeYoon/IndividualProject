package controll;

import java.util.ArrayList;

import model.CustomerVO;
import model.OrderVO;

public class ConnectionData {
	static CustomerVO customerVO;//스테이지 변경시 넘겨줄 데이터 고객
	static ArrayList<OrderVO> orderVOList;//스테이지 변경시 넘겨줄 데이터 주문
	static boolean sceneChange;//스테이지 변경시 넘겨줄때 true
	static boolean editOrder;//주문수정일 경우 true
	static int mainOrOrder;//이전 스테이지 구별 ---main에서 이동시 1 , 주문창에서 이동시 2
	static String newPhone;//새 고객이 주문 전화가 왔을때 전화번호 저장
	static boolean newCustomer;//새 고객이 주문 전화가 왔을때 true
	static String writerPath;//Excel 경로 저장
}
