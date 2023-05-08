<%@page import="util.connectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="controller.QueDTO" %>
<%@page import="controller.QueDAO" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="controller.CommDTO" %>
<%@page import="controller.CommDAO" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>202044036_final</title>
</head>
<body>
	<%		
		String userMail = null;
		if(session.getAttribute("mail") != null){
			userMail = (String)session.getAttribute("mail");
		}
	
		String qId = null;
		if(request.getParameter("que_id") != null){
			qId = request.getParameter("que_id");
		}
		if(request.getParameter("que_id") == null){
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('잘못된 접근입니다.');");
			writer.println("location.href = 'index.jsp';");
			writer.println("</script>");
			writer.close();
		}
		
		QueDTO queV = new QueDAO().getView(qId);
		
	%>
	<jsp:include page="/header.jsp" flush="false"/>
	<table class="table mt-3">
  		<thead>
    		<tr>
      			<th scope="col">Question</th>
    		</tr>
  		</thead>
  		<tbody>
    		<tr>
      			<td class="col-sm-3">Title</td>
 				<td class="col-sm-9"><%= queV.getqTitle() %></td>
    		</tr>
    		<tr>
      			<td class="col-sm-3">Name</td>
      			<td class="col-sm-9"><%= queV.getmName() %></td>
    		</tr>
    		<tr>
      			<td class="col-sm-3">Date</td>
      			<td class="col-sm-9"><%= queV.getqDate() %></td>
    		</tr>
    		<tr>
      			<td class="col-sm-3">Content</td>
      			<td class="col-sm-9"><%= queV.getqContent() %></td>
    		</tr>
    		<tr>
      			<td class="col-sm-3"></td>
      			<td class="col-sm-9"><img src="upload/<%= queV.getqImage() %>"></td>
    		</tr>
  		</tbody>
	</table>
	<a href="queForm.jsp" class="btn btn-primary mx-2">List</a>
	<%
		String uName = null;
		String sql = String.format("select member_name from member where member_mail='%s'", userMail);
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = connectDB.getCon();
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				uName = rs.getString(1);
			}
			rs.close();
			stat.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(uName.equals(queV.getmName())){
	%>
		<!-- <button type="submit" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateQue">Update</button> -->
		<a class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#updateQue">Update</a>
		<a href="queDeleteAtcion?que_id=<%= queV.getqId() %>" class="btn btn-secondary">Delete</a>
	<%
		}
	%>
	<div class="modal" id="updateQue" tabindex="-1">
 		<div class="modal-dialog">
    		<div class="modal-content">
    			<div class="modal-header">
       				<h5 class="modal-title">Questions</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
       				<form action="queUpdateAction" method="post" enctype="multipart/form-data">
       					<div class="row">
       						<div class="form-group col-sm-12">
       							<label> 제목 </label>
       							<input type="text" name="queTitle" class="form-control mt-1" maxlength="20" value="<%=queV.getqTitle() %>">
       						</div>
       					</div>
       					<div class="form-group col-sm-12">
       						<label> 내용</label>
       						<textarea name="queContent" class="form-control mt-1" maxlength="2048" rows="5" ><%=queV.getqContent() %></textarea>
       					</div>
       					<div class="form-group col-sm-12">
       							<label> 이미지 첨부</label>
       							<input type="file" name="queFile" class="form-control mt-1" maxlength="20" value="<%queV.getqImage(); %>">
       					</div>			
       					<div class="form-group col-sm-12">
       							<label> 게시물 번호</label>
       							<input type="text" name="queId" class="form-control mt-1" maxlength="20" readonly value="<%=queV.getqId() %>">
       					</div>			
       					<div class="modal-footer">
        					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        					<button type="submit" class="btn btn-primary">Update</button>
      					</div>	
       				</form>
      			</div>
    		</div>
  		</div>
	</div>
	<form action="commWriteAction?que_id=<%= queV.getqId() %>" method="post">
		<div class="input-group mt-3 m-3">
			<textarea class="form-control" aria-label="With textarea" name="commText"></textarea>
			<button type="submit" class="btn btn-primary mx-2">Comment</button>
		</div>
	</form>
	<%
		ArrayList<CommDTO> commList = new ArrayList<CommDTO>();
		commList = new CommDAO().getList(queV.getqId());	
		
		if(commList != null){
			for(int i = 0; i < commList.size(); i++){
				CommDTO comm = commList.get(i);
	%>
	<div class="container">
		<div class="row">
			<table class="table table-bordered mt-3">
				<tbody>
					<tr>
      					<td class="col-sm-9">Name : <%=comm.getMember_name() %></td>
      					<td class="col-sm-3">Date : <%=comm.getComm_date() %></td>
    				</tr>
    				<tr>
    					<td class="col-sm-3" colspan="2"><%=comm.getComm_text() %></td>
    				</tr>
				</tbody>
			
			</table>
		</div>
	</div>
	<%
			}
		}
		
	%>
</body>
</html>