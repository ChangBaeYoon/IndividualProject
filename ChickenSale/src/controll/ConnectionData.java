package controll;

import java.util.ArrayList;

import model.CustomerVO;
import model.OrderVO;

public class ConnectionData {
	static CustomerVO customerVO;//�������� ����� �Ѱ��� ������ ��
	static ArrayList<OrderVO> orderVOList;//�������� ����� �Ѱ��� ������ �ֹ�
	static boolean sceneChange;//�������� ����� �Ѱ��ٶ� true
	static boolean editOrder;//�ֹ������� ��� true
	static int mainOrOrder;//���� �������� ���� ---main���� �̵��� 1 , �ֹ�â���� �̵��� 2
	static String newPhone;//�� ���� �ֹ� ��ȭ�� ������ ��ȭ��ȣ ����
	static boolean newCustomer;//�� ���� �ֹ� ��ȭ�� ������ true
	static String writerPath;//Excel ��� ����
}
