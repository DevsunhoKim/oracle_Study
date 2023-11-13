package com.sist.dao;
import java.util.*;
import java.sql.*;
public class MovieDAO {
	//오라클 연결
	private Connection conn;
	//SQL 송수신
	private PreparedStatement ps;
	//오라클 주소
	String URL="jdbc:oracle:thin:@localhost:1521:XE";
	 public MovieDAO() {
	 try
	 {
		 
	 }catch(Exception ex) {}
	}
	 public void getConnection()
	 {
		 try
		 {
			 conn=DriverManager.getConnection(URL,"hr","happy");
			 // conn hr/happy
		 }catch(Exception ex) {}
	 }
	 
	 public void disConnection()
	 {
		 try
		 {
			 if(ps!=null) ps.close();
			 if(conn!=null) conn.close();
		 }catch(Exception ex) {}
	 }
	// 검색 => 여러개 ArrayList , 한 개 VO => 영화 1개에 대한 정보
	 public ArrayList<MovieVO> movieFindData(String column,String fd)
	 {
		 ArrayList<MovieVO> list=
				 new ArrayList<MovieVO>();
		 try
		 {
			 getConnection();
			 String sql="SELECT title,genre,actor "+"FROM movie "+
			            "WHERE " +column +" LIKE '%'||?||'%'";
			 // 테이블명 / 컬럼명은 문자열 결합 => 검색어는 ''
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, fd);
			 // 결과값 받기 => ?가 있는데 값을 설정하지 않는 경우에는 오류 발생
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 //rs.next() => 한 줄씩 읽어 온다
				 MovieVO vo=new MovieVO();
				 vo.setTitle(rs.getString(1));
				 vo.setGenre(rs.getString(2));
				 vo.setActor(rs.getString(3));
				 
				 list.add(vo);
			 }
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }finally
		 {
			 disConnection();
		 }
		 return list;
	 }
	 public int movieFindCount(String column,String fd)
	 {
		 int count=0;
		 try
		 {
			 getConnection();
			 String sql="SELECT COUNT(*) "+"FROM movie "+ "WHERE"+ column +
					 "LIKE '%'||?||'%'";
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, fd);
			 ResultSet rs=ps.executeQuery();
			 rs.next();
			 count=rs.getInt(1);
			 rs.close();
		 }catch(Exception ex)
		 {
			 ex.printStackTrace();
		 }finally
		 {
			 disConnection();
		 }
		 return count;
	 }
}
