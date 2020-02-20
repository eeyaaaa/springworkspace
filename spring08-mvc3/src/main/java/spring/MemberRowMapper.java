package spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//MemberDao에 메서드 중복코드를 줄이고 효율적으로 코드작성을 위해 중복을 모아두는 클래스 
public class MemberRowMapper implements RowMapper<Member>{	
	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(
				rs.getString("EMAIL"),
				rs.getString("PASSWORD"),
				rs.getString("NAME"),
				rs.getTimestamp("REGDATE"));
		member.setId(rs.getLong("ID"));
				
		return member;
	}

}
