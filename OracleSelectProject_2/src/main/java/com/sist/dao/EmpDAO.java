package com.sist.dao;
//VO => table(column) : DOA 는 한 개만 만들 수 있다
/*
 *  조인 
 *   => 2개 이상의 테이블을 연결해서 하나의 테이블처럼 필요한 데이터를 추출
 *   => 데이터 추출에 목적
 *   => 종류 
 *       INNER JOIN
 *         => EQUI JOIN (=)
 *         => NON EQUI JOIN (논리연산자,BETWEEN~END)
 *         => NULL 값일 경우에 처리가 안된다
 *       OUTER JOIN
 *         => NULL 값 처리 => INNER JOIN을 보완
 *         => LEFT OUTER JOIN
 *         => RIGHT OUTER JOIN
 */
import java.util.*;
import java.sql.*;
public class EmpDAO {
	// 오라클 연결 
	private Connection conn;
	// SQL 문장 전송 => 결과값
	private PreparedStatement ps;
	//오라클 연결 => URL 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 1. 드라이버 등록 => 각데이터베이스 업체에서 제공
	// 한번만 사용 ==> 생성자에서 주로 사용
	public EmpDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// thin / oci
			// thin => 연결만 하는 역할 (무료)
			// oci => 드라이버에 오라클 기능 (유료)
			// 클래스명으로 메모리 할당 => 스프링 , 마이바티스 => 라이브러리에서 주로 사용
			// 리플렉션 => 메모리 할당 => 클래스 등록 (XML)
		}catch(Exception ex) {}
	}
	// 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// 오라클로 전송하는 명령어 => conn hr/happy
		}catch(Exception ex) {}
	}
	// 오라클 닫기
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			// OutputStream / BufferedReader
			if(conn!=null) conn.close(); 
			// Socket
		}catch(Exception ex) {}
	}
	///////////// => DAO의 필수 과정
	/*
	 *  DAO => 원래 한 개의 테이블만 제어할 수 있게 만든다
	 *      => 통합해서 사용
	 *         게시판 + 댓글
	 *         찜하기 + 좋아요 => Service
	 *  ***** DAO vs Service
	 *  회원 / 게시판 / 영화 / 음악 / 맛집 / 레시피
	 *  => 재사용
	 */
	// DAO(JDBC) ==> DBCP ==> ORM (MyBatis=> JPA)
	//          200줄               100줄       10줄
	public ArrayList<EmpVO> empAllData()
	{
		// emp,dept,salgrade 정보를 한번에 모아서 => 전송
		ArrayList<EmpVO> list=
				new ArrayList<EmpVO>();
		try
		{
			// 1. 연결
			getConnection();
			// 2. sql 문장 제작
			String sql="SELECT empno,ename,job,hiredate,"
					+"sal,emp.deptno,dname,loc,grade "
					+"FROM emp,dept,salgrade "
					+"WHERE emp.deptno=dept.deptno "
					+"AND sal BETWEEN losal AND hisal";
			ps=conn.prepareStatement(sql);
			// 오라클 전송
			ResultSet rs=ps.executeQuery();
			//실행 후에 결과값을 받아온다
			while(rs.next())
			{
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				vo.setDeptno(rs.getInt(6));
				vo.getDvo().setDname(rs.getString(7));
				vo.getDvo().setLoc(rs.getString(8));
				vo.getSvo().setGrade(rs.getInt(9));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			disConnection();
		}
		return list;
	}
}
