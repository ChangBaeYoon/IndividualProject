package controll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MenuVO;

public class MenuDAO {
	//�޴��߰�
	public MenuVO getMenuRegiste(MenuVO mvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MENU");
		sql.append("(M_NUM,M_NAME,M_PRICE,M_BTN)");
		sql.append(" VALUES (M_NUM_SEQ.nextval, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MenuVO mVo = mvo;

		try {
			// 3 DBUtil�̶�� Ŭ������ getConnection()�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 4 �Է¹��� �л� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getPrice());
			pstmt.setString(3, mVo.getBtn());

			// 5 SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return mVo;
	}

	//�޴� ����
	public MenuVO getMenuEdit(MenuVO mvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MENU SET ");
		sql.append(" M_NAME= ?,M_PRICE =?");
		sql.append(" WHERE M_BTN = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		MenuVO mVo = null;

		try {
			// 3 DBUtil�̶�� Ŭ������ getConnection()�޼���� �����ͺ��̽��� ����
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 4 �Է¹��� �л� ������ ó���ϱ� ���Ͽ� SQL������ ����
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getName());
			pstmt.setString(2, mvo.getPrice());
			pstmt.setString(3, mvo.getBtn());

			// 5 SQL���� ������ ó�� ����� ����
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("�޴� ���� �Ϸ�.");
				alert.setContentText("�޴� ���� ����!!!");
				alert.showAndWait();
				mVo = new MenuVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("�޴� ���� ����.");
				alert.setContentText("�޴� ���� ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return mVo;
	}

	// �޴� ����
	public void getMenuDelete(MenuVO mvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM MENU WHERE M_BTN = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getBtn());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("�޴� ���� �Ϸ�.");
				alert.setContentText("�޴� ���� ����!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("�޴� ����");
				alert.setHeaderText("�޴� ���� ����.");
				alert.setContentText("�޴� ���� ����!!!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	
	// �ԷµǾ� �ִ� �޴� ã��
	public ArrayList<MenuVO> getMenuCheck() throws Exception {
		ArrayList<MenuVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM MENU WHERE M_NAME IS NOT NULL ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mVo = new MenuVO();
				mVo.setNum(rs.getInt("M_NUM"));
				mVo.setName(rs.getString("M_NAME"));
				mVo.setPrice(rs.getString("M_PRICE"));
				mVo.setBtn(rs.getString("M_BTN"));
				list.add(mVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}
	
	//�޴� ��ư�̸����� ã��
	public MenuVO getMenuBtn(String btnNum) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM MENU WHERE M_BTN = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, btnNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVo = new MenuVO();
				mVo.setNum(rs.getInt("M_NUM"));
				mVo.setName(rs.getString("M_NAME"));
				mVo.setPrice(rs.getString("M_PRICE"));
				mVo.setBtn(rs.getString("M_BTN"));
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return mVo;
	}
	
	//�޴� �̸����� ã��
	public MenuVO getMenuName(String name) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM MENU WHERE M_NAME = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MenuVO mVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				mVo = new MenuVO();
				mVo.setNum(rs.getInt("M_NUM"));
				mVo.setName(rs.getString("M_NAME"));
				mVo.setPrice(rs.getString("M_PRICE"));
				mVo.setBtn(rs.getString("M_BTN"));
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 �����ͺ��̽����� ���ῡ ���Ǿ��� ������Ʈ�� ����
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return mVo;
	}
}
