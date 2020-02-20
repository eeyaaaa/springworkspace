package spring;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDaoImpl implements MemberDao{
	
	//xml파일에서 DataSource를 받아 동작하던 SqlSessionTemplate를 받아 사용
	private SqlSessionTemplate sqlSessionTemplae;

	
	public MemberDaoImpl(SqlSessionTemplate sqlSessionTemplae) {
		super();
		this.sqlSessionTemplae = sqlSessionTemplae;
	}

	@Override
	public void update(Member member) {
		sqlSessionTemplae.update("update",member);
		
	}

	@Override
	public void insert(Member member) {
		sqlSessionTemplae.insert("insert",member);
		
	}

	@Override
	public Object selectByEmail(String email) {
		return sqlSessionTemplae.selectOne("selectByEmail",email);
	}

	@Override
	public List<Member> selectAll() {
		return sqlSessionTemplae.selectList("list");
	}

	@Override
	public int count() {
		return sqlSessionTemplae.selectOne("count");
	}

	@Override
	public List<Member> selectByRegdate(Date from, Date to) {
		HashMap<String,Date> map = new HashMap<String, Date>();
		map.put("from", from);
		map.put("to", to);
		return sqlSessionTemplae.selectList("selectByRegdate",map);
	}

	@Override
	public Member selectById(Long id) {
		List<Member> results = sqlSessionTemplae.selectList("selectById",id);
		return results.isEmpty() ? null : results.get(0);
	}

}
