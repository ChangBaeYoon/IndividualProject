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
	//메뉴추가
	public MenuVO getMenuRegiste(MenuVO mvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO MENU");
		sql.append("(M_NUM,M_NAME,M_PRICE,M_BTN)");
		sql.append(" VALUES (M_NUM_SEQ.nextval, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		MenuVO mVo = mvo;

		try {
			// 3 DBUtil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 4 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getPrice());
			pstmt.setString(3, mVo.getBtn());

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
		return mVo;
	}

	//메뉴 수정
	public MenuVO getMenuEdit(MenuVO mvo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE MENU SET ");
		sql.append(" M_NAME= ?,M_PRICE =?");
		sql.append(" WHERE M_BTN = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		MenuVO mVo = null;

		try {
			// 3 DBUtil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// sVo = new StudentVO();

			// 4 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getName());
			pstmt.setString(2, mvo.getPrice());
			pstmt.setString(3, mvo.getBtn());

			// 5 SQL문을 수행후 처리 결과를 얻어옴
			int i = pstmt.executeUpdate();

			if (i == 1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("메뉴 수정");
				alert.setHeaderText("메뉴 수정 완료.");
				alert.setContentText("메뉴 수정 성공!!!");
				alert.showAndWait();
				mVo = new MenuVO();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("메뉴 수정");
				alert.setHeaderText("메뉴 수정 실패.");
				alert.setContentText("메뉴 수정 실패!!!");
				alert.showAndWait();
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
		return mVo;
	}

	// 메뉴 삭제
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
				alert.setTitle("메뉴 삭제");
				alert.setHeaderText("메뉴 삭제 완료.");
				alert.setContentText("메뉴 삭제 성공!!!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("메뉴 삭제");
				alert.setHeaderText("메뉴 삭제 실패.");
				alert.setContentText("메뉴 삭제 실패!!!");
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
	
	// 입력되어 있는 메뉴 찾기
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
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return list;
	}
	
	//메뉴 버튼이름으로 찾기
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
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return mVo;
	}
	
	//메뉴 이름으로 찾기
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
				// 6 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
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
