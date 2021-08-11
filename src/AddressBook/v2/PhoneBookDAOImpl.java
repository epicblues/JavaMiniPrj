package AddressBook.v2;

import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class PhoneBookDAOImpl implements PhoneBookDAO {

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dbUrl,"c##bituser","bituser");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<PhoneBookVO>();
		
		try {
			conn = getConnection();
			st = conn.createStatement();
			String query = "SELECT id,RPAD(name,10,' '),substr(hp,1,length(hp)-7) || '**' || substr(hp,length(hp)-4,3) || '**',"
					+ "substr(tel,1,length(tel)-7) || '**' || substr(tel,length(tel)-4,3) || '**' "
					+ "FROM phone_book ORDER BY id";
			rs = st.executeQuery(query);
			while(rs.next()) {
				list.add(new PhoneBookVO(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	}

	@Override
	public List<PhoneBookVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<PhoneBookVO>();
		
		try {
			conn = getConnection();
			String sql = "SELECT id,RPAD(name,10,' '),substr(hp,1,length(hp)-7) || '**' || substr(hp,length(hp)-4,3) || '**',"
					+ "substr(tel,1,length(tel)-7) || '**' || substr(tel,length(tel)-4,3) || '**' "
					+ "FROM phone_book" +" WHERE name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new PhoneBookVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			String query = "INSERT INTO phone_book VALUES (seq_phone_book.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result == 1;
	}

	

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			String query = "DELETE FROM phone_book WHERE id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return result == 1;
	}

}
