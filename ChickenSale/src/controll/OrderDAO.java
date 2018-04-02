package controll;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.OrderVO;

public class OrderDAO {
	// 주문 입력
	public void getOrderRegiste(OrderVO ovo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ORDERS");
		sql.append(" VALUES (O_NUMBER_SEQ.nextval, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		OrderVO oVo = ovo;

		try {
			// 3 DBUtil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, oVo.getmNum());
			pstmt.setInt(2, oVo.getcNum());
			pstmt.setInt(3, oVo.getoNum());
			pstmt.setString(4, oVo.getPhone());
			pstmt.setString(5, oVo.getAddress());
			pstmt.setString(6, oVo.getContents());
			pstmt.setString(7, oVo.getPrice());
			pstmt.setString(8, oVo.getCash());
			pstmt.setInt(9, oVo.getQuantity());

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

	// 주문 수정용 입력
	public void getOrderEditRegiste(OrderVO ovo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ORDERS");
		sql.append(" VALUES (O_NUMBER_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		Connection con = null;
		PreparedStatement pstmt = null;
		OrderVO oVo = ovo;

		try {
			// 3 DBUtil이라는 클래스의 getConnection()메서드로 데이터베이스와 연결
			con = DBUtil.getConnection();

			// 4 입력받은 학생 정보를 처리하기 위하여 SQL문장을 생성
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, oVo.getmNum());
			pstmt.setInt(2, oVo.getcNum());
			pstmt.setInt(3, oVo.getoNum());
			pstmt.setDate(4, (Date) oVo.getDate());
			pstmt.setString(5, oVo.getPhone());
			pstmt.setString(6, oVo.getAddress());
			pstmt.setString(7, oVo.getContents());
			pstmt.setString(8, oVo.getPrice());
			pstmt.setString(9, oVo.getCash());
			pstmt.setInt(10, oVo.getQuantity());

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

	// 주문내역 전체
	public ArrayList<OrderVO> getOrderTotal() throws Exception {
		ArrayList<OrderVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
		sql.append(
				"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
		sql.append("FROM ORDERS ");
		sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
		sql.append("ORDER BY TO_CHAR(O_DATE,'YYYY-MM-DD') DESC ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setoNum(rs.getInt(1));
				oVo.setDate(rs.getDate(2));
				oVo.setPhone(rs.getString(3));
				oVo.setAddress(rs.getString(4));
				oVo.setContents(rs.getString(5));
				oVo.setPrice(rs.getString(6));
				oVo.setCash(rs.getString(7));
				list.add(oVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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

	// 주문내역 전화번호 검색
	public ArrayList<OrderVO> getOrderPhoneSearch(String phone) throws Exception {
		ArrayList<OrderVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
		sql.append(
				"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
		sql.append("FROM ORDERS ");
		sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
		sql.append("HAVING O_PHONE = ?");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setoNum(rs.getInt(1));
				oVo.setDate(rs.getDate(2));
				oVo.setPhone(rs.getString(3));
				oVo.setAddress(rs.getString(4));
				oVo.setContents(rs.getString(5));
				oVo.setPrice(rs.getString(6));
				oVo.setCash(rs.getString(7));
				list.add(oVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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

	// 주문번호 검색
	public ArrayList<OrderVO> getOrderONum(int oNum) throws Exception {
		ArrayList<OrderVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM ORDERS ");
		sql.append("WHERE O_NUM = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, oNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setoNumber(rs.getInt("O_NUMBER"));
				oVo.setmNum(rs.getInt("M_NUM"));
				oVo.setcNum(rs.getInt("C_NUM"));
				oVo.setoNum(rs.getInt("O_NUM"));
				oVo.setDate(rs.getDate("O_DATE"));
				oVo.setPhone(rs.getString("O_PHONE"));
				oVo.setAddress(rs.getString("O_ADDRESS"));
				oVo.setContents(rs.getString("O_CONTENTS"));
				oVo.setPrice(rs.getString("O_PRICE"));
				oVo.setCash(rs.getString("O_CASH"));
				oVo.setQuantity(rs.getInt("O_QUANTITY"));
				list.add(oVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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

	// 전화번호로 검색 후 수정
	public void getCustomerOrderEdit(String address, String phone) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ORDERS SET ");
		sql.append("O_ADDRESS = ? ");
		sql.append("WHERE O_PHONE = ? ");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, address);
			pstmt.setString(2, phone);
			
			int i = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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
	}

	// 주문 삭제
	public void getOrderDelete(int num) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ORDERS WHERE O_NUM = ?");
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			int i = pstmt.executeUpdate();

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

	// 주문내역 금일
	public ArrayList<OrderVO> getOrderToday() throws Exception {
		ArrayList<OrderVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
		sql.append(
				"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
		sql.append("FROM ORDERS ");
		sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
		sql.append("HAVING TO_CHAR(O_DATE,'YYYY-MM-DD') = TO_CHAR(SYSDATE,'YYYY-MM-DD')");

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setoNum(rs.getInt(1));
				oVo.setDate(rs.getDate(2));
				oVo.setPhone(rs.getString(3));
				oVo.setAddress(rs.getString(4));
				oVo.setContents(rs.getString(5));
				oVo.setPrice(rs.getString(6));
				oVo.setCash(rs.getString(7));
				list.add(oVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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

	// 주문내역 날짜별 검색
	public ArrayList<OrderVO> getOrderDate(Date date1, Date date2, int i) throws Exception {
		ArrayList<OrderVO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OrderVO oVo = null;
		try {
			switch (i) {
			case 1:
				sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
				sql.append(
						"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
				sql.append("FROM ORDERS ");
				sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
				sql.append(
						"HAVING TO_CHAR(O_DATE,'YYYY-MM-DD') >= TO_CHAR(?,'YYYY-MM-DD') AND TO_CHAR(O_DATE,'YYYY-MM-DD') <= TO_CHAR(?,'YYYY-MM-DD')");
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setDate(1, date1);
				pstmt.setDate(2, date2);
				break;
			case 2:
				sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
				sql.append(
						"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
				sql.append("FROM ORDERS ");
				sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
				sql.append("HAVING TO_CHAR(O_DATE,'YYYY-MM-DD') >= TO_CHAR(?,'YYYY-MM-DD') ");
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setDate(1, date1);
				break;
			case 3:
				sql.append("SELECT O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') ,O_PHONE,O_ADDRESS,");
				sql.append(
						"SUBSTR(XMLAGG(XMLELEMENT(COL ,'+', O_CONTENTS) ORDER BY O_NUM).EXTRACT('//text()').GETSTRINGVAL(), 2) O_CONTENTS , SUM(O_PRICE) , O_CASH ");
				sql.append("FROM ORDERS ");
				sql.append("GROUP BY O_NUM, TO_CHAR(O_DATE,'YYYY-MM-DD') , O_PHONE, O_ADDRESS, O_CASH ");
				sql.append("HAVING TO_CHAR(O_DATE,'YYYY-MM-DD') <= TO_CHAR(?,'YYYY-MM-DD')");
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setDate(1, date2);
				break;
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				oVo = new OrderVO();
				oVo.setoNum(rs.getInt(1));
				oVo.setDate(rs.getDate(2));
				oVo.setPhone(rs.getString(3));
				oVo.setAddress(rs.getString(4));
				oVo.setContents(rs.getString(5));
				oVo.setPrice(rs.getString(6));
				oVo.setCash(rs.getString(7));
				list.add(oVo);
			}

		} catch (SQLException e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
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
}
