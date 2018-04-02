package controll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.CustomerVO;

public class CustomerDAO {
	// 고객리스트 입력
	public void getCustomerRegiste(CustomerVO cvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO CUSTOMER");
		sql.append("(C_NUM,C_NAME,C_ADDRESS,C_PHONE,C_OTHER)");
		sql.append(" VALUES (C_NUM_SEQ.nextval, ?, ?, ?,?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		CustomerVO cVo = cvo;

		try {
			// 3 DBUtil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 4 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cVo.getName());
			pstmt.setString(2, cVo.getAddress());
			pstmt.setString(3, cVo.getPhone());
			pstmt.setString(4, cVo.getOther());

			// 5 SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 고객리스트 검색
	public ArrayList<CustomerVO> getCustomerCheck(String check, String row) throws Exception {
		ArrayList<CustomerVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		switch (check) {
		case "C_NUM":
			sql.append("SELECT * FROM CUSTOMER WHERE C_NUM LIKE ? ORDER BY C_NUM ");
			break;
		case "C_NAME":
			sql.append("SELECT * FROM CUSTOMER WHERE C_NAME LIKE ? ORDER BY C_NUM ");
			break;
		case "C_ADDRESS":
			sql.append("SELECT * FROM CUSTOMER WHERE C_ADDRESS LIKE ? ORDER BY C_NUM ");
			break;
		case "C_PHONE":
			sql.append("SELECT * FROM CUSTOMER WHERE C_PHONE LIKE ? ORDER BY C_NUM ");
			break;
		case "C_OTHER":
			sql.append("SELECT * FROM CUSTOMER WHERE C_OTHER LIKE ? ORDER BY C_NUM ");
			break;
		}

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + row + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setNum(rs.getInt("C_NUM"));
				cVo.setName(rs.getString("C_NAME"));
				cVo.setAddress(rs.getString("C_ADDRESS"));
				cVo.setPhone(rs.getString("C_PHONE"));
				cVo.setOther(rs.getString("C_OTHER"));
				list.add(cVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}

	// 고객리스트 전체
	public ArrayList<CustomerVO> getCustomerTotal() throws Exception {
		ArrayList<CustomerVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM CUSTOMER ORDER BY C_NUM");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cVo = new CustomerVO();
				cVo.setNum(rs.getInt("C_NUM"));
				cVo.setName(rs.getString("C_NAME"));
				cVo.setAddress(rs.getString("C_ADDRESS"));
				cVo.setPhone(rs.getString("C_PHONE"));
				cVo.setOther(rs.getString("C_OTHER"));
				list.add(cVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}

	// 고객 전화번호로 검색
	public CustomerVO getCustomerPhone(String phone) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM CUSTOMER WHERE C_PHONE = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phone);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVo = new CustomerVO();
				cVo.setNum(rs.getInt("C_NUM"));
				cVo.setName(rs.getString("C_NAME"));
				cVo.setAddress(rs.getString("C_ADDRESS"));
				cVo.setPhone(rs.getString("C_PHONE"));
				cVo.setOther(rs.getString("C_OTHER"));
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return cVo;
	}

	// 고객정보 수정
	public void getCustomerEdit(CustomerVO cvo) throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE CUSTOMER SET ");
		sql.append(" C_NAME= ?,C_ADDRESS =?,C_PHONE =?,C_OTHER =?");
		sql.append(" WHERE C_NUM = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		CustomerVO cVo = cvo;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, cVo.getName());
			pstmt.setString(2, cVo.getAddress());
			pstmt.setString(3, cVo.getPhone());
			pstmt.setString(4, cVo.getOther());
			pstmt.setInt(5, cVo.getNum());

			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}

	// 고객 삭제
	public void getCustomerDelete(int num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM CUSTOMER WHERE C_NUM = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객정보 삭제");
				alert.setHeaderText("고객정보 삭제 완료.");
				alert.setContentText("고객정보 삭제 성공!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("고객정보 삭제");
				alert.setHeaderText("고객정보 삭제 실패.");
				alert.setContentText("고객정보 삭제 실패!!!");
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
	
	//고객번호로 찾기
	public CustomerVO getCustomerSearch(int num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM CUSTOMER WHERE C_NUM = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerVO cVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cVo = new CustomerVO();
				cVo.setNum(rs.getInt("C_NUM"));
				cVo.setName(rs.getString("C_NAME"));
				cVo.setAddress(rs.getString("C_ADDRESS"));
				cVo.setPhone(rs.getString("C_PHONE"));
				cVo.setOther(rs.getString("C_OTHER"));
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
		} finally {
			try {
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return cVo;
	}
}
