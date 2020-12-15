package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.BlueDto;

public class BlueDao {

	// 필드
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// 그 동안 사용하던 DriverManager 클래스를 사용하지 않기 때문에
	// getConnection() 메소드를 만들어서 사용하지 않는다.
	
	// DBCP 방식은 Connection을 DataSource 클래스가 관리한다.
	// 앞으로는 DataSource 객체가 제공하는 getConnection() 메소드를 사용한다.
	
	// DataSource 객체 만들기 (새로운 작업)
	private static DataSource dataSource;
	// static { }  // static 블록에서 static 필드의 초기화를 할 수 있다.
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			// Tomcat의 경우 java:comp/env/ 를 prefix로 사용한다.
			// context.xml의 <Resource>태그의 name속성이 jdbc/oracle이다.
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// Singleton pattern
	private BlueDao() { }
	private static BlueDao blueDao = new BlueDao();
	public static BlueDao getInstance() {
		return blueDao;
	}
	
	// 메소드
	/***** 1. 접속 종료 *****/
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) { rs.close(); }
			if (ps != null) { ps.close(); }
			if (con != null) { con.close(); }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***** 2. 목록 가져오기 *****/
	public ArrayList<BlueDto> list() {
		ArrayList<BlueDto> list = new ArrayList<BlueDto>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BLUE";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// rs.next() -> BlueDto -> list.add
			while ( rs.next() ) {
				BlueDto blueDto = new BlueDto();
				blueDto.setNo( rs.getInt("NO") );  // rs.getInt(1)
				blueDto.setWriter( rs.getString("WRITER") );
				blueDto.setTitle( rs.getString("TITLE") );
				blueDto.setContent( rs.getString("CONTENT") );
				blueDto.setPostDate( rs.getDate("POSTDATE") );
				list.add(blueDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	
	
	
	
	
	
	
}