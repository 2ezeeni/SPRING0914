package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.WhiteDto;
import mybatis.config.DBService;

public class WhiteDao {

	// 필드
	private SqlSessionFactory factory;
	
	// 생성자(Singleton)
	private WhiteDao() {
		factory = DBService.getInstance().getFactory();
	}
	private static WhiteDao whiteDao = new WhiteDao();
	public static WhiteDao getInstance() {
		return whiteDao;
	}
	
	// 메소드
	public List<WhiteDto> list() {
		SqlSession ss = factory.openSession();
		List<WhiteDto> list = ss.selectList("mybatis.mapper.white.getList");
		ss.close();
		return list;
	}
	
	
	
	
	
	
	
	
	
	

}
