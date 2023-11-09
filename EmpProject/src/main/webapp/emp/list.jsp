<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" import="java.util.*,com.sist.dao.*"%>
<%
	// �� �޴� ��ġ 
	EmpDAO dao=new EmpDAO();
	ArrayList<EmpVO> list=dao.empListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 600px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>��� ����</h1>
		<div class="row">
			<table class="table">
				<tr>
					<th class="text-center">���</th>
					<th class="text-center">�̸�</th>
					<th class="text-center">����</th>
					<th class="text-center">�Ի���</th>
					<th class="text-center">�޿�</th>
				</tr>
				<%
					for(EmpVO vo:list){
				%>
					<tr>
						<td class="text-center"><%=vo.getEmpno() %></td>
						<td class="text-center"><%=vo.getEname() %></td>
						<td class="text-center"><%=vo.getJob() %></td>
						<td class="text-center"><%=vo.getHiredate() %></td>
						<td class="text-center"><%=vo.getSal() %></td>
					</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>
</body>
</html>