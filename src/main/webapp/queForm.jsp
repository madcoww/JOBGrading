<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="controller.QueDTO" %>
<%@page import="controller.QueDAO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.net.URLEncoder" %>
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
		if(session.getAttribute("mail") == null){
			PrintWriter writer = response.getWriter();
			writer.println("<script>");
			writer.println("alert('로그인을 해주세요.');");
			writer.println("location.href = 'index.jsp';");
			writer.println("</script>");
			writer.close();
		}
	%>
	<jsp:include page="/header.jsp" flush="false"/>
	<section class="container">
		<form method="get" action="#" class="form-inline mt-3">
			<button type="submit" class="btn btn-primary mx-1 mt-2" style="float: right;" data-bs-toggle="modal" data-bs-target="#registerQue">글쓰기</button>
		</form>
	</section>
	<table class="table mt-3">
  		<thead>
    		<tr>
      			<th scope="col">No</th>
      			<th scope="col">Title</th>
      			<th scope="col">Name</th>
      			<th scope="col">Date</th>
    		</tr>
  		</thead>
  		<tbody>
  			<%
				ArrayList<QueDTO> queList = new ArrayList<QueDTO>();
				queList = new QueDAO().getList();
				if(queList != null){
					for(int i = 0; i < queList.size(); i++){
						QueDTO que = queList.get(i);
			%>
    		<tr>
      			<th scope="row"><%= que.getqId() %></th>
      			<td><a href="queContentView.jsp?que_id=<%= que.getqId() %>"><%= que.getqTitle() %></a></td>
      			<td><%= que.getmName() %></td>
      			<td><%= que.getqDate() %></td>
    		</tr>
    		<%
					}
				}
			%>
  		</tbody>
	</table>
	<div class="modal" id="registerQue" tabindex="-1">
 		<div class="modal-dialog">
    		<div class="modal-content">
    			<div class="modal-header">
       				<h5 class="modal-title">Questions</h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
       				<form action="queWriteAction" enctype="multipart/form-data" method="post"> <!-- queWriteServlet -->
       					<div class="row">
       						<div class="form-group col-sm-12">
       							<label> 제목</label>
       							<input type="text" name="queTitle" class="form-control mt-1" maxlength="20">
       						</div>
       					</div>
       					<div class="form-group col-sm-12">
       						<label> 내용</label>
       						<textarea name="queContent" class="form-control mt-1" maxlength="2048" rows="5"></textarea>
       					</div>
       					<div class="form-group col-sm-12">
       							<label> 이미지 첨부</label>
       							<input type="file" name="queFile" class="form-control mt-1" maxlength="20">
       						</div>
       					<div class="modal-footer">
        					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        					<button type="submit" class="btn btn-primary">Register</button>
      					</div>	
       				</form>
      			</div>
    		</div>
  		</div>
	</div>
</body>
</html>