package spring;


import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//해당하는 이메일 하나만 검색하는 ㅓ기능 
	public Member selectByEmail(String email) {
		
		List<Member> result = jdbcTemplate.query("select * from MEMBER where EMAIL=?", 
				new MemberRowMapper()  //이부분 부터 MemverRowMapper에 메소드 내용으로 대체{
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member member = new Member(
//						rs.getString("EMAIL"),
//						rs.getString("PASSWORD"),
//						rs.getString("NAME"),
//						rs.getTimestamp("REGDATE"));
//				member.setId(rs.getLong("ID"));
//						
//				return member;
//			}
//			
//		여기까지 중복 부분 대체}
				,email);
		return result.isEmpty() ? null : result.get(0);
	}
	
	//Member에 모든 정보를 출력
	public List<Member> selectAll(){
		List<Member> result = jdbcTemplate.query("select * from MEMBER", 
				new MemberRowMapper() //이부분 부터 중복 부분 대체 {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member member = new Member(
//						rs.getString("EMAIL"),
//						rs.getString("PASSWORD"),
//						rs.getString("NAME"),
//						rs.getTimestamp("REGDATE"));
//				member.setId(rs.getLong("ID"));
//						
//				return member;
//			}
//			
//		}
				);
		return result;
	}
	
	//조회 결과가 한개의 행일때 사용하는 메서드
	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER",Integer.class);
		return count;
	}
	
	//변경된 행의 개수를 반환
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME=?, PASSWORD=? where EMAIL=?",
				member.getName(),member.getPassword(),member.getEmail());
	}
	
	//KeyHolder를 사용하여 자동으로 생성되는 키 값을 구하는 기능
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){
							/*PreparedStatement객체를 사용하기위해 익명클래스사용
							 * (preparedStatementCreator는 인터페이스 */ 
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				//파라미터로 전달받은 Connection을 이용해서 PreparedStatement생성
				PreparedStatement pstmt = con.prepareStatement(
						"insert into MEMBER (ID,EMAIL,PASSWORD,NAME,REGDATE)"+
				"values (MEMBER_SEQ.nextval,?,?,?,?)", new String[] {"ID"});
				
				//인덱스 파라미터 값 설정
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, new Timestamp(member.getRegisterDate().getTime()));
				
				//생성한 PreparedStatement 객체 반환
				return pstmt;
			}
			
		},keyHolder);
		Number keyValue = keyHolder.getKey();
		System.out.println(keyValue);
		member.setId(keyValue.longValue());
	}
}
